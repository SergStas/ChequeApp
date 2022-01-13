package com.example.chequeapp.validation.newevent

import java.util.*

class NewEventNamesPageValidator: INewEventNamesPageValidator {
    override fun validate(eventName: String?, date: Date?): INewEventNamesPageValidator.Result =
        when {
            eventName.isNullOrEmpty() -> INewEventNamesPageValidator.Result.NameIsEmpty
            date == null -> INewEventNamesPageValidator.Result.DateIsEmpty
            else -> INewEventNamesPageValidator.Result.Ok
        }
}