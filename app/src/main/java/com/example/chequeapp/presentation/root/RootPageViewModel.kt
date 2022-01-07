package com.example.chequeapp.presentation.root

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.ui.models.AppSection
import com.example.domain.models.UserData
import com.example.domain.repository.IUserRepository

class RootPageViewModel(
    private val userRepository: IUserRepository
): AbstractRootPageViewModel() {
    override val userLive = MutableLiveData<UserData?>()
    override val activePageLive = MutableLiveData<AppSection>()

    init {
        checkUser()
    }

    override fun switchPage(appSection: AppSection) {
        activePageLive.value = appSection
    }

    override fun checkUser() {
        userLive.value = userRepository.getLoggedInUser()
    }
}