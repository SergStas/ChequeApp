package com.example.chequeapp.presentation.newevent.names

import androidx.lifecycle.MutableLiveData
import java.util.*

abstract class AbstractNevEventNamesPageViewModel {
    abstract val eventNameLive: MutableLiveData<String>
    abstract val dateLive: MutableLiveData<Date>
    abstract val calendarVisibilityLive: MutableLiveData<Boolean>
    abstract val errorMessageLive: MutableLiveData<String>

    abstract fun switchCalendarVisibility()
    abstract fun nextPage()
    abstract fun changeDate(date: Date)
    abstract fun setName(name: String)
}