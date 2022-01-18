package com.example.chequeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.chequeapp.R
import com.example.chequeapp.adapters.utils.DefaultItemCallback
import com.example.chequeapp.adapters.utils.DefaultViewHolder
import com.example.domain.models.UserData
import kotlinx.android.synthetic.main.fragment_participants_user_bar.view.*

class NewEventParticipantsAdapter(
    private val onClick: (UserData) -> Unit,
): ListAdapter<UserData, DefaultViewHolder>(DefaultItemCallback<UserData>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder =
        DefaultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_participants_user_bar, parent, false)
        )

    override fun onBindViewHolder(holderDefault: DefaultViewHolder, position: Int) {
        val user = getItem(position)
        holderDefault.containerView.run {
            pub_tv_name?.text = user.name
            pub_iv_close?.setOnClickListener { onClick(user) }
        }
    }
}