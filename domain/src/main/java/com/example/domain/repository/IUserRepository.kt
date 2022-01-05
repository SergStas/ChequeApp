package com.example.domain.repository

import com.example.domain.models.UserData

interface IUserRepository {
    fun getUser(): UserData?

    fun saveUser(userData: UserData)
}