package com.example.chequeapp.domain.repository

import com.example.chequeapp.domain.models.UserData

interface IUserRepository {
    fun getUser(): UserData?

    fun saveUser(userData: UserData)
}