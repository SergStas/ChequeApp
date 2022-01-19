package com.example.chequeapp.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.chequeapp.R
import com.example.chequeapp.adapters.utils.DefaultSpinnerAdapter
import com.example.chequeapp.adapters.utils.DefaultViewHolder
import com.example.chequeapp.models.newevent.PositionDataElement
import com.example.domain.models.SessionData.PartData
import com.example.domain.models.UserData
import kotlinx.android.synthetic.main.fragment_position_bar.view.*

class NewEventReceiptPositionsAdapter(
    private val onPositionTitleChanged: (Int, String) -> Unit,
    private val onPositionPriceChanged: (Int, Float) -> Unit,
    private val onNewUserSelected: (Int, String) -> Unit,
    private val onAddButtonClicked: (Int) -> Unit,
    private val onPartRemoved: (Int, PartData) -> Unit,
    private val onPartValueChanged: (Int, PartData, Float) -> Unit,
    private val onDeletePositionClicked: (Int) -> Unit,
): AbstractNewEventReceiptPositionsAdapter() {

    override fun updatePosition(position: PositionDataElement) {
        val index = currentList.indexOfFirst { p -> p.id == position.id }
        if (index == -1) return
        val item = getItem(index)
        item.position = position.position
        item.msg = position.msg
        notifyItemChanged(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder =
        DefaultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_position_bar, parent, false)
        )

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val positionData = getItem(position)
        holder.containerView.run {
            position_et_title?.addTextChangedListener(getEditTextOnChangeListener { e ->
                onPositionTitleChanged(positionData.id, e ?: "")
            })

            position_et_price?.addTextChangedListener(getEditTextOnChangeListener { e ->
                onPositionPriceChanged(positionData.id, e?.toFloat() ?: 0f)
            })

            setSpinner(holder, positionData)

            position_tv_error?.text = positionData.msg ?: ""
            position_tv_error?.isVisible = !positionData.msg.isNullOrEmpty()

            position_b_add_part?.setOnClickListener { onAddButtonClicked(positionData.id) }
            position_iv_delete?.setOnClickListener { onDeletePositionClicked(positionData.id) }

            position_rv_parts?.run {
                val listAdapter = NewEventPositionPartsAdapter(
                    onDeleteClicked = { p -> onPartRemoved(positionData.id, p) },
                    onValueChanged = { p, f -> onPartValueChanged(positionData.id, p, f) }
                )
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false,
                )
                adapter = listAdapter
                listAdapter.submitList(positionData.position.parts)
            }
        }
    }

    private fun setSpinner(holder: DefaultViewHolder, positionData: PositionDataElement) {
        holder.containerView.run {
            receipt_new_part_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val name = parent?.getItemAtPosition(position) as String
                    if (name != context.getString(R.string.new_users_spinner_empty_ph))
                        onNewUserSelected(positionData.id, name)
                }
            }
            val users = positionData.position.parts.map(PartData::user)
            receipt_new_part_spinner.adapter = DefaultSpinnerAdapter(users.map(UserData::name))
        }
    }

    private fun getEditTextOnChangeListener(action: (String?) -> Unit) =
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                action(p0?.toString())
            }
        }

}