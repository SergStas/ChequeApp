package com.example.chequeapp.presentation.root

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.models.navigation.AppSection
import com.example.domain.models.UserData

abstract class AbstractRootPageViewModel {
    abstract val userLive: MutableLiveData<UserData?>
    abstract val activePageLive: MutableLiveData<AppSection>

    abstract fun switchPage(appSection: AppSection)

    abstract fun checkUser()
}