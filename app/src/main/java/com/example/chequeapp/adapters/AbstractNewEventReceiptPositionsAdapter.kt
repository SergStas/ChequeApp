package com.example.chequeapp.adapters

import androidx.recyclerview.widget.ListAdapter
import com.example.chequeapp.adapters.utils.DefaultItemCallback
import com.example.chequeapp.adapters.utils.DefaultViewHolder
import com.example.chequeapp.models.newevent.PositionDataElement

abstract class AbstractNewEventReceiptPositionsAdapter:
    ListAdapter<PositionDataElement, DefaultViewHolder>(DefaultItemCallback<PositionDataElement>()),
    INewEventReceiptPositionsAdapter