package com.example.chequeapp.ui.newevent.receipts

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.adapters.AbstractNewEventReceiptPositionsAdapter
import com.example.chequeapp.adapters.NewEventReceiptPositionsAdapter
import com.example.chequeapp.adapters.utils.DefaultSpinnerAdapter
import com.example.chequeapp.presentation.newevent.receipts.AbstractNewEventNewReceiptViewModel
import com.example.chequeapp.ui.root.AbstractRootActivity
import com.example.domain.models.UserData
import kotlinx.android.synthetic.main.fragment_new_receipt.*
import javax.inject.Inject

class NewReceiptFragment(
    private val parentActivity: AbstractRootActivity,
) : Fragment(R.layout.fragment_new_receipt) {
    @Inject
    lateinit var viewModel: AbstractNewEventNewReceiptViewModel

    private lateinit var adapter: AbstractNewEventReceiptPositionsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()
        setListAdapter()
        setPayerSpinner()
        subscribe()
        setListeners()
    }

    private fun setListeners() {
        receipt_et_title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                viewModel.changeTitle(p0?.toString() ?: "")
            }
        })
        receipt_b_add.setOnClickListener {
            val title = receipt_et_position?.text?.toString()
            if (!title.isNullOrEmpty()) {
                viewModel.createPosition(title)
            }
        }
        receipt_b_confirm.setOnClickListener {
            viewModel.finish()
        }
    }

    private fun subscribe() {
        viewModel.run {
            titleLive.observe(parentActivity) { t -> receipt_et_title.setText(t) }

            pageErrorLive.observe(parentActivity) { msg ->
                if (!msg.isNullOrEmpty()) {
                    receipt_tv_error.text = msg
                }
                receipt_tv_error.isVisible = msg.isNullOrEmpty()
            }

            positionsLive.observe(parentActivity, adapter::submitList)
            lastModifiedPosition.observe(parentActivity) { pos ->
                if (pos != null) {
                    adapter.updatePosition(pos)
                }
            }
        }
    }

    private fun setPayerSpinner() {
        receipt_payer_spinner?.run {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val name = parent?.getItemAtPosition(position) as String
                    if (name != parentActivity.getString(R.string.new_users_spinner_empty_ph))
                        viewModel.changePayer(name)
                }
            }
            val availableUsers = viewModel.participants.toMutableList()
                .apply { remove(viewModel.payerLive.value) }
            val names = if (availableUsers.isEmpty()) {
                listOf(context.getString(R.string.new_users_spinner_empty_ph))
            } else {
                availableUsers.map(UserData::name)
            }
            adapter = DefaultSpinnerAdapter(names)
        }
    }

    private fun setListAdapter() {
        initAdapter()
        receipts_rv_positions?.let {
            it.layoutManager = LinearLayoutManager(
                parentActivity,
                LinearLayoutManager.VERTICAL,
                false,
            )
            it.adapter = adapter
        }
    }

    private fun initAdapter() {
        adapter = NewEventReceiptPositionsAdapter(
            onNewUserSelected = viewModel::changeSelectedUser,
            onPositionPriceChanged = viewModel::changePositionPrice,
            onPositionTitleChanged = viewModel::changePositionTitle,
            onAddButtonClicked = { id ->
                val user = viewModel.selectedUsersMapLive.value?.get(id) ?: ""
                viewModel.addParticipantToPosition(id, user)
            },
            onPartRemoved = { id, part ->
                viewModel.deleteParticipant(id, part.user.name)
            },
            onPartValueChanged = { id, part, percentage ->
                viewModel.changePart(id, part.user.name, percentage)
            },
            onDeletePositionClicked = viewModel::deletePosition
        )
    }

    private fun inject() =
        (parentActivity.application as App).appComponent.inject(this)
}