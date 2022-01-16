package com.example.chequeapp.presentation.newevent.participants

import androidx.lifecycle.MutableLiveData
import com.example.domain.models.UserData

abstract class AbstractNewEventParticipantsPageViewModel {
    abstract val loadingLive: MutableLiveData<Boolean>
    abstract val availableUsersLive: MutableLiveData<List<UserData>>
    abstract val addedUsersLive: MutableLiveData<List<UserData>>
    abstract val errorMessageLive: MutableLiveData<String>

    abstract fun loadUsers()
    abstract fun addUser(userData: UserData)
    abstract fun removeUser(userData: UserData)
    abstract fun nextPage()
    abstract fun back()
}

