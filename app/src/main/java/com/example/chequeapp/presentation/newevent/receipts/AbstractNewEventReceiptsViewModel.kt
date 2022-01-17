package com.example.chequeapp.presentation.newevent.receipts

import androidx.lifecycle.MutableLiveData
import com.example.domain.models.SessionData

abstract class AbstractNewEventReceiptsViewModel {
    abstract val receiptsLive: MutableLiveData<List<SessionData.ReceiptData>>
    abstract val errorMessageLive: MutableLiveData<String>

    abstract fun addReceipt(name: String)
    abstract fun removeReceipt(name: String)
    abstract fun finish()
    abstract fun edit(name: String)
    abstract fun back()
}

