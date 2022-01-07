package com.example.data.storage

import android.content.SharedPreferences
import com.example.data.models.User

class SharedPrefsUserStorage(
    private val sharedPreferences: SharedPreferences
): IDeviceUserStorage {
    companion object {
        private const val USERNAME_KEY = "username"
    }

    override fun saveUser(user: User) =
        sharedPreferences.edit().putString(USERNAME_KEY, user.name).apply()

    override fun getUser(): User? {
        val username = sharedPreferences.getString(USERNAME_KEY, null)
        return if (username == null) null else User(username)
    }
}
