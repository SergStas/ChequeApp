package com.example.chequeapp.presentation.newevent

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.domain.models.EventData

class NewEventViewModel: AbstractNewEventViewModel() {
    override val eventDataLive = MutableLiveData(EventData.empty())
    override val pageLive = MutableLiveData(NewEventPage.Names)

    override fun switchPage(page: NewEventPage) {
        pageLive.value = page
    }

    override fun submitChanges(eventData: EventData) {
        eventDataLive.value = eventData
    }
}