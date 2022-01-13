package com.example.chequeapp.presentation.newevent.names

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.R
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.chequeapp.presentation.newevent.AbstractNewEventViewModel
import com.example.chequeapp.validation.newevent.INewEventNamesPageValidator
import java.util.*

class NewEventNamesPageViewModel(
    private val context: Context,
    private val rootViewModel: AbstractNewEventViewModel,
    private val validator: INewEventNamesPageValidator,
): AbstractNevEventNamesPageViewModel() {
    override val eventNameLive = MutableLiveData("")
    override val dateLive = MutableLiveData(Date(System.currentTimeMillis()))
    override val calendarVisibilityLive = MutableLiveData(false)
    override val errorMessageLive = MutableLiveData("")

    override fun switchCalendarVisibility() {
        calendarVisibilityLive.value = !calendarVisibilityLive.value!!
    }

    override fun nextPage() {
        when (validator.validate(eventNameLive.value, dateLive.value)) {
            INewEventNamesPageValidator.Result.Ok -> {
                errorMessageLive.value = ""
                rootViewModel.switchPage(NewEventPage.Participants)
            }
            INewEventNamesPageValidator.Result.DateIsEmpty ->
                errorMessageLive.value = context.getString(R.string.new_names_error_date_is_null)
            INewEventNamesPageValidator.Result.NameIsEmpty ->
                errorMessageLive.value = context.getString(R.string.new_names_error_name_is_null)
        }
    }

    override fun changeDate(date: Date) {
        dateLive.value = date
    }

    override fun setName(name: String) {
        eventNameLive.value = name
    }
}