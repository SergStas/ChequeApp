package com.example.chequeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.chequeapp.R
import com.example.chequeapp.adapters.utils.DefaultItemCallback
import com.example.chequeapp.adapters.utils.DefaultViewHolder
import com.example.chequeapp.utils.extensions.setOnValueChangedListener
import com.example.chequeapp.utils.extensions.toFloatOrNullWithComma
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
            ppb_et_part.setText("%.2f".format(item.part / 100))
            ppb_et_part.setOnValueChangedListener { v ->
                onValueChanged(item, v.toFloatOrNullWithComma() ?: 0f)
            }
        }
    }
}