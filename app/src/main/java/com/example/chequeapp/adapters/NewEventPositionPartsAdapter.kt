package com.example.chequeapp.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.chequeapp.R
import com.example.chequeapp.adapters.utils.DefaultItemCallback
import com.example.chequeapp.adapters.utils.DefaultViewHolder
import com.example.domain.models.SessionData
import kotlinx.android.synthetic.main.fragment_position_part_bar.view.*

class NewEventPositionPartsAdapter(
    private val onDeleteClicked: (SessionData.PartData) -> Unit,
    private val onValueChanged: (SessionData.PartData, Float) -> Unit,
): ListAdapter<SessionData.PartData, DefaultViewHolder>(DefaultItemCallback<SessionData.PartData>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder =
        DefaultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_position_part_bar, parent, false)
        )

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val item = getItem(position)
        holder.containerView.run {
            ppb_iv_delete.setOnClickListener { onDeleteClicked(item) }
            ppb_tv_user_name.text = item.user.name
            ppb_et_part.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    onValueChanged(item, p0?.toString()?.toFloat() ?: 0f)
                }
            })
        }
    }
}