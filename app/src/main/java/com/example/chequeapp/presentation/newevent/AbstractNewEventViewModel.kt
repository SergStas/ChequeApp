package com.example.chequeapp.presentation.newevent

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.domain.models.EventData
import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import java.util.*

abstract class AbstractNewEventViewModel {
    abstract val eventDataLive: MutableLiveData<EventData>
    abstract val pageLive: MutableLiveData<NewEventPage>

    abstract fun switchPage(page: NewEventPage)
    abstract fun submitNameAndDate(name: String, date: Date)
    abstract fun submitUsers(users: List<UserData>)
    abstract fun submitReceipts(receipts: List<SessionData.ReceiptData>)
    abstract fun finishAndCalculate()
}