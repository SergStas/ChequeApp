package com.example.chequeapp.presentation.newevent.receipts

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.chequeapp.presentation.newevent.AbstractNewEventViewModel
import com.example.domain.models.SessionData

class NewEventReceiptsViewModel(
    private val context: Context,
    private val rootViewModel: AbstractNewEventViewModel,
): AbstractNewEventReceiptsViewModel() {
    override var activeReceipt: SessionData.ReceiptData? = null
    override val receiptsLive = MutableLiveData<List<SessionData.ReceiptData>>(emptyList())
    override val errorMessageLive = MutableLiveData("")

    override fun addReceipt(name: String) {
        val names = receiptsLive.value?.map(SessionData.ReceiptData::name) ?: emptyList()

        if (names.contains(name)) {
            errorMessageLive.value = context.getString(R.string.new_receipts_error_unique)
        } else {
            errorMessageLive.value = ""
            receiptsLive.value = receiptsLive.value?.toMutableList()
                ?.apply { add(SessionData.ReceiptData.namedAs(name)) }
        }
    }

    override fun removeReceipt(name: String) {
        receiptsLive.value = receiptsLive.value?.toMutableList()
            ?.apply { remove(SessionData.ReceiptData.namedAs(name)) }
    }

    override fun finish() {
        if (receiptsLive.value.isNullOrEmpty()) {
            errorMessageLive.value = context.getString(R.string.new_receipts_error_no_receiptrs)
        } else {
            errorMessageLive.value = ""
            rootViewModel.submitReceipts(receiptsLive.value!!)
            rootViewModel.finishAndCalculate()
        }
    }

    override fun edit(name: String) {
        val receipt = receiptsLive.value?.firstOrNull { r -> r.name == name }
        if (receipt == null) {
            App.toast("Unexpected error has occurred")
        } else {
            activeReceipt = receipt
            rootViewModel.switchPage(NewEventPage.NewReceipt)
        }
    }

    override fun back() {
        rootViewModel.switchPage(NewEventPage.Participants)
    }
}