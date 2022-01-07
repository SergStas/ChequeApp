package com.example.domain.models.auth

import android.service.autofill.UserData

sealed class LoginResult {
    data class Success(val user: UserData): LoginResult()

    object UserNotFound: LoginResult()

    data class Error(val throwable: Throwable): LoginResult()
}