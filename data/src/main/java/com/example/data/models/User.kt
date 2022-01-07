package com.example.data.models

import com.example.domain.models.UserData

data class User(
    val name: String,
) {
    companion object {
        fun fromUserData(user: UserData): User =
            User(user.name)
    }

    fun toUserData(): UserData =
        UserData(name)
}