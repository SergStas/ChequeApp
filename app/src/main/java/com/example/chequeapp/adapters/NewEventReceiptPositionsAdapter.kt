package com.example.chequeapp.adapters

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.chequeapp.R
import com.example.chequeapp.adapters.utils.DefaultItemCallback
import com.example.chequeapp.adapters.utils.DefaultViewHolder
import com.example.chequeapp.models.newevent.PositionDataWithErrorMsg
import com.example.domain.models.SessionData.PartData
import com.example.domain.models.SessionData.PositionData
import com.example.domain.models.UserData
import kotlinx.android.synthetic.main.fragment_position_bar.view.*

class NewEventReceiptPositionsAdapter(
    private val onPositionTitleChanged: (PositionData, String) -> Unit,
    private val onPositionPriceChanged: (PositionData, Float) -> Unit,
    private val onNewUserSelected: (PositionData, String) -> Unit,
    private val onAddButtonClicked: (PositionData) -> Unit,
    private val onPartRemoved: (PositionData, PartData) -> Unit,
    private val onPartValueChanged: (PositionData, PartData, Float) -> Unit,
): ListAdapter<PositionDataWithErrorMsg, DefaultViewHolder>(DefaultItemCallback<PositionDataWithErrorMsg>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder =
        DefaultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_position_bar, parent, false)
        )

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val positionData = getItem(position)
        holder.containerView.run {
            position_et_title?.addTextChangedListener(editTextOnChange { e ->
                onPositionTitleChanged(positionData.position, e ?: "")
            })

            position_et_price?.addTextChangedListener(editTextOnChange { e ->
                onPositionPriceChanged(positionData.position, e?.toFloat() ?: 0f)
            })

            setSpinner(holder, positionData.position)

            position_tv_error?.text = positionData.msg ?: ""
            position_tv_error?.isVisible = !positionData.msg.isNullOrEmpty()

            position_b_add_part?.setOnClickListener { onAddButtonClicked(positionData.position) }

            position_rv_parts?.run {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false,
                )
                adapter = NewEventPositionPartsAdapter(
                    onDeleteClicked = { p -> onPartRemoved(positionData.position, p) },
                    onValueChanged = { p, f -> onPartValueChanged(positionData.position, p, f) }
                )
                (adapter as ListAdapter<PartData, DefaultViewHolder>)
                    .submitList(positionData.position.parts)
            }
        }
    }

    fun updatePosition(positionName: String, value: PositionData) {
        val index = currentList.indexOfFirst { p -> p.position.name == positionName }
        if (index == -1) return
        val item = getItem(index)
        item.position = value
        notifyItemChanged(index)
    }

    fun updatePositionErrorMessage(positionName: String, msg: String) {
        val index = currentList.indexOfFirst { p -> p.position.name == positionName }
        if (index == -1) return
        val item = getItem(index)
        item.msg = msg
        notifyItemChanged(index)
    }

    private fun setSpinner(holder: DefaultViewHolder, positionData: PositionData) {
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
                        onNewUserSelected(positionData, name)
                }
            }
            val users = positionData.parts.map(PartData::user)
            receipt_new_part_spinner.adapter =
                ArrayAdapter(
                    context,
                    R.layout.support_simple_spinner_dropdown_item,
                    if (users.isEmpty()) {
                        listOf(context.getString(R.string.new_users_spinner_empty_ph))
                    } else {
                        users.map(UserData::name)
                    },
                ).apply { setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item) }
        }
    }

    private fun editTextOnChange(action: (String?) -> Unit) =
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                action(p0?.toString())
            }
        }
}