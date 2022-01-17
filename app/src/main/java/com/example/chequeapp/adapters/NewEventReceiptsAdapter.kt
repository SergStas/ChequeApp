package com.example.chequeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.chequeapp.R
import com.example.chequeapp.adapters.utils.DefaultItemCallback
import com.example.chequeapp.adapters.utils.ViewHolder
import com.example.domain.models.SessionData
import kotlinx.android.synthetic.main.fragment_receipts_preview_bar.view.*

class NewEventReceiptsAdapter(
    private val onClickEdit: (SessionData.ReceiptData) -> Unit,
    private val onClickRemove: (SessionData.ReceiptData) -> Unit,
): ListAdapter<SessionData.ReceiptData, ViewHolder>(DefaultItemCallback<SessionData.ReceiptData>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_receipts_preview_bar, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.containerView.run {
            rpb_iv_cross.setOnClickListener { onClickRemove(item) }
            rpb_b_edit.setOnClickListener { onClickEdit(item) }
            rpb_tv_title.text = item.name
            rpb_tv_pos_cnt.text = "${item.positions.size} positions"
            rpb_tv_price.text = "${item.positions.sumOf { p -> p.price.toDouble() }.toString()} RUB"
        }
    }
}

