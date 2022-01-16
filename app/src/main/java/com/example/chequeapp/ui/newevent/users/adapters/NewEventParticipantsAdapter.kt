package com.example.chequeapp.ui.newevent.users.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chequeapp.R
import com.example.domain.models.UserData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_participants_user_bar.view.*

class NewEventParticipantsAdapter(
    private val onClick: (UserData) -> Unit,
): ListAdapter<UserData, NewEventParticipantsAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_participants_user_bar, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.containerView.run {
            pub_tv_name?.text = user.name
            pub_iv_close?.setOnClickListener { onClick(user) }
        }
    }

    class ViewHolder(override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer
}