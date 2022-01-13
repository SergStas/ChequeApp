package com.example.chequeapp.presentation.newevent

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.domain.models.EventData

abstract class AbstractNewEventViewModel {
    abstract val eventDataLive: MutableLiveData<EventData>
    abstract val pageLive: MutableLiveData<NewEventPage>

    abstract fun switchPage(page: NewEventPage)
    abstract fun submitChanges(eventData: EventData)
}