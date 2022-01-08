package com.example.chequeapp.presentation.auth

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.models.auth.AuthPage
import com.example.domain.models.auth.LoginParams
import com.example.domain.models.auth.LoginResult
import com.example.domain.models.auth.RegistrationParams
import com.example.domain.models.auth.RegistrationResult
import com.example.domain.repository.IUserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(
    private val userRepository: IUserRepository,
): AbstractAuthViewModel() {
    override val pageLive = MutableLiveData(AuthPage.Menu)
    override val loginResultLive = MutableLiveData<LoginResult?>(null)
    override val registrationResultLive = MutableLiveData<RegistrationResult?>(null)
    override val loadingStateLive = MutableLiveData(false)

    private var loginDisposable: Disposable? = null
    private var registrationDisposable: Disposable? = null

    override fun switchPage(page: AuthPage) {
        pageLive.value = page
    }

    override fun login(username: String) {
        loadingStateLive.value = true
        loginDisposable = Observable.create<LoginResult> { emitter ->
            emitter.onNext(userRepository.loginUser(LoginParams(username)))
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { result -> loginResultLive.value = result },
                { t -> loginResultLive.value = LoginResult.Error(t) },
                {
                    loadingStateLive.value = false
                    loginDisposable?.dispose()
                },
            )
    }

    override fun register(username: String, telegramTag: String) {
        loadingStateLive.value = true
        val replaced = telegramTag.replace("@", "")
        registrationDisposable = Observable.create<RegistrationResult> { emitter ->
            emitter.onNext(userRepository.register(RegistrationParams(username, replaced)))
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { result -> registrationResultLive.value = result },
                { registrationResultLive.value = RegistrationResult.UnknownError },
                {
                    loadingStateLive.value = false
                    registrationDisposable?.dispose()
                },
            )
    }
}