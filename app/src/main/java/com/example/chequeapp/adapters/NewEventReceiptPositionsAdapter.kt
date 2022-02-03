package com.example.chequeapp.adapters

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chequeapp.R
import com.example.chequeapp.adapters.utils.DefaultSpinnerAdapter
import com.example.chequeapp.adapters.utils.DefaultViewHolder
import com.example.chequeapp.models.newevent.PositionDataElement
import com.example.chequeapp.utils.extensions.setOnValueChangedListener
import com.example.chequeapp.utils.extensions.toFloatOrNullWithComma
import com.example.domain.models.SessionData.PartData
import com.example.domain.models.UserData
import kotlinx.android.synthetic.main.fragment_position_bar.view.*

class NewEventReceiptPositionsAdapter(
    private val participants: List<UserData>,
    private val onPositionTitleChanged: (Int, String) -> Unit,
    private val onPositionPriceChanged: (Int, Float) -> Unit,
    private val onNewUserSelected: (Int, String) -> Unit,
    private val onAddButtonClicked: (Int) -> Unit,
    private val onPartRemoved: (Int, PartData) -> Unit,
    private val onPartValueChanged: (Int, PartData, Float) -> Unit,
    private val onDeletePositionClicked: (Int) -> Unit,
): AbstractNewEventReceiptPositionsAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder =
        DefaultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_position_bar, parent, false)
        )

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val positionData = getItem(position)
        holder.containerView.run {
            position_et_title?.setText(positionData.position.name)
            position_et_title?.setOnValueChangedListener{ e ->
                onPositionTitleChanged(positionData.id, e)
            }

            position_et_price.setText(positionData.position.price.toString())
            position_et_price?.setOnValueChangedListener { e ->
                onPositionPriceChanged(positionData.id, e.toFloatOrNullWithComma() ?: 0f)
            }

            setSpinner(holder, positionData)

            position_tv_error?.text = positionData.msg ?: ""
            position_tv_error?.isVisible = !positionData.msg.isNullOrEmpty()

            position_b_add_participant?.setOnClickListener { onAddButtonClicked(positionData.id) }
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
            val users = participants.toMutableList()
                .apply {
                    removeAll(positionData.position.parts.map(PartData::user))
                    ifEmpty { add(UserData((context.getString(R.string.new_users_spinner_empty_ph)))) }
                }
                .toMutableList()
            val names = users.map(UserData::name)
            receipt_new_part_spinner.adapter = DefaultSpinnerAdapter(
                context = context,
                labels = names,
            )
            val selectedUser = users.firstOrNull { u -> u.name == positionData.selectedUserData?.name }
            if (selectedUser != null) {
                receipt_new_part_spinner.setSelection(names.indexOf(selectedUser.name))
            }
        }
    }
}