package com.example.chequeapp.adapters.utils

import androidx.recyclerview.widget.DiffUtil

class DefaultItemCallback<T>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}