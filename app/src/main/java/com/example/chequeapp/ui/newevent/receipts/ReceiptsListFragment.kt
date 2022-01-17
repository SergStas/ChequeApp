package com.example.chequeapp.ui.newevent.receipts

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.adapters.NewEventReceiptsAdapter
import com.example.chequeapp.presentation.newevent.receipts.AbstractNewEventReceiptsViewModel
import com.example.chequeapp.ui.root.AbstractRootActivity
import kotlinx.android.synthetic.main.fragment_new_event_names.*
import kotlinx.android.synthetic.main.fragment_new_receipt.*
import kotlinx.android.synthetic.main.fragment_position_bar.*
import kotlinx.android.synthetic.main.fragment_receipts_list.*
import kotlinx.android.synthetic.main.fragment_receipts_preview_bar.*
import javax.inject.Inject

class ReceiptsListFragment(
    private val parentActivity: AbstractRootActivity,
) : Fragment(R.layout.fragment_receipts_list) {
    @Inject
    lateinit var viewModel: AbstractNewEventReceiptsViewModel

    private lateinit var adapter: NewEventReceiptsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()
        setAdapter()
        subscribeOnViewModel()
        setListeners()
    }

    private fun setListeners() {
        new_receipts_iv_add.setOnClickListener {
            val name = new_receipts_et_add.text.toString()
            if (name.isNotEmpty()) {
                viewModel.addReceipt(name)
                new_receipts_et_add.setText("")
            }
        }
        new_b_back_to_users.setOnClickListener {
            viewModel.back()
        }
        new_b_finish.setOnClickListener {
            viewModel.finish()
        }
    }

    private fun subscribeOnViewModel() {
        viewModel.errorMessageLive.observe(parentActivity) { msg ->
            new_receipts_tv_error.isVisible = !msg.isNullOrEmpty()
            new_receipts_tv_error.text = msg
        }
        viewModel.receiptsLive.observe(parentActivity) { list ->
            adapter.submitList(list)
            new_receipts_tv_empty.isVisible = list.isNullOrEmpty()
        }
    }

    private fun setAdapter() {
        new_receipts_rv?.let {
            it.layoutManager = LinearLayoutManager(
                parentActivity,
                LinearLayoutManager.VERTICAL,
                false,
            )
            adapter = NewEventReceiptsAdapter(
                onClickEdit = { r -> viewModel.edit(r.name) },
                onClickRemove = { r -> viewModel.removeReceipt(r.name) },
            )
            it.adapter = adapter
        }
    }

    private fun inject() =
        (parentActivity.application as App).appComponent.inject(this)
}