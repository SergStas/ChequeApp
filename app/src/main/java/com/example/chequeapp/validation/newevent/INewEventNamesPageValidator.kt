package com.example.chequeapp.validation.newevent

import java.util.*

interface INewEventNamesPageValidator {
    fun validate(eventName: String?, date: Date?): Result

    enum class Result {
        Ok,
        NameIsEmpty,
        DateIsEmpty,
    }
}

