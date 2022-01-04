package com.example.chequeapp.domain.usecases.auth

import com.example.chequeapp.domain.models.common.NetworkRequestActions
import com.example.chequeapp.domain.network.AbstractApiAccessObject
import com.example.chequeapp.domain.network.models.responses.LoginResponse
import com.example.chequeapp.domain.repository.IUserRepository
import retrofit2.Callback

class LoginUseCase(
    private val userRepository: IUserRepository,
    private val apiAccessObject: AbstractApiAccessObject,
) {
    fun execute(username: String, actions: NetworkRequestActions<LoginResponse>) {
        actions.preloadAction()
        val call = apiAccessObject.cApi.login(username)
        call.enqueue(getCallbackWithDataSaving(actions))
    }

    private fun getCallbackWithDataSaving(
        actions: NetworkRequestActions<LoginResponse>,
    ): Callback<LoginResponse> =
        NetworkRequestActions(
            preloadAction = actions.preloadAction,
            onSuccessAction = { call, response ->
                if (response.body()?.userData != null)
                    userRepository.saveUser(response.body()!!.userData!!)
                actions.onSuccessAction(call, response)
            },
            onFailureAction = actions.onFailureAction
        ).toCallback()
}