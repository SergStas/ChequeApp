package com.example.chequeapp.adapters.utils

import androidx.recyclerview.widget.DiffUtil

class DefaultDiffUtilCallback<T>(
    private val old: List<T>,
    private val new: List<T>,
): DiffUtil.Callback() {
    private val itemCallback = DefaultItemCallback<T>()

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        itemCallback.areItemsTheSame(old[oldItemPosition], new[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        itemCallback.areContentsTheSame(old[oldItemPosition], new[newItemPosition])
}