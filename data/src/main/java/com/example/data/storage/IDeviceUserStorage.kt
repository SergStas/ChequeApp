package com.example.data.storage

import com.example.data.models.User

interface IDeviceUserStorage {
    fun saveUser(user: User)

    fun getUser(): User?
}