package com.example.domain.repository

import com.example.domain.models.auth.LoginParams
import com.example.domain.models.UserData
import com.example.domain.models.auth.LoginResult
import com.example.domain.models.auth.RegistrationParams
import com.example.domain.models.auth.RegistrationResult

interface IUserRepository {
    fun getLoggedInUser(): UserData?

    fun loginUser(params: LoginParams): LoginResult

    fun register(params: RegistrationParams): RegistrationResult

    fun getAllUsers(): List<UserData>
}