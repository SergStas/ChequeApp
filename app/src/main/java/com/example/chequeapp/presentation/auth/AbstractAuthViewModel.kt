package com.example.chequeapp.presentation.auth

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.models.auth.AuthPage
import com.example.domain.models.auth.LoginResult
import com.example.domain.models.auth.RegistrationResult

abstract class AbstractAuthViewModel {
    abstract val pageLive: MutableLiveData<AuthPage>
    abstract val loginResultLive: MutableLiveData<LoginResult?>
    abstract val registrationResultLive: MutableLiveData<RegistrationResult?>
    abstract val loadingStateLive: MutableLiveData<Boolean>

    abstract fun switchPage(page: AuthPage)

    abstract fun login(username: String)

    abstract fun register(username: String, telegramTag: String)
}