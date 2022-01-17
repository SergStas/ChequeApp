package com.example.chequeapp.presentation.newevent

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.App
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.domain.models.EventData
import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import java.util.*

class NewEventViewModel: AbstractNewEventViewModel() {
    override val eventDataLive = MutableLiveData(EventData.empty())
    override val pageLive = MutableLiveData(NewEventPage.Names)

    override fun switchPage(page: NewEventPage) {
        pageLive.value = page
    }

    override fun submitNameAndDate(name: String, date: Date) {
        eventDataLive.value?.sessionData?.name = name
        eventDataLive.value?.sessionData?.date = date.time
    }

    override fun submitUsers(users: List<UserData>) {
        eventDataLive.value?.sessionData?.participants = users
    }

    override fun submitReceipts(receipts: List<SessionData.ReceiptData>) {
        eventDataLive.value?.sessionData?.receipts = receipts
    }

    override fun finishAndCalculate() {
        App.toast("Not yet implemented")
    }
}