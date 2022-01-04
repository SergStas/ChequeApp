package com.example.chequeapp.domain.usecases.auth

import com.example.chequeapp.domain.models.UserData
import com.example.chequeapp.domain.models.common.NetworkRequestActions
import com.example.chequeapp.domain.network.AbstractApiAccessObject
import com.example.chequeapp.domain.network.models.RegistrationData
import com.example.chequeapp.domain.network.models.responses.LoginResponse
import com.example.chequeapp.domain.network.models.responses.RegistrationResponse
import com.example.chequeapp.domain.repository.IUserRepository
import retrofit2.Callback

class RegisterUseCase(
    private val userRepository: IUserRepository,
    private val apiAccessObject: AbstractApiAccessObject,
) {
    fun execute(user: RegistrationData, actions: NetworkRequestActions<RegistrationResponse>) {
        actions.preloadAction()
        val call = apiAccessObject.cApi.register(user)
        call.enqueue(getCallbackWithDataSaving(actions, user))
    }

    private fun getCallbackWithDataSaving(
        actions: NetworkRequestActions<RegistrationResponse>,
        user: RegistrationData,
    ): Callback<RegistrationResponse> =
        NetworkRequestActions(
            preloadAction = actions.preloadAction,
            onSuccessAction = { call, response ->
                val status = response.body()?.status
                if (status == RegistrationResponse.RegistrationStatus.Ok)
                    userRepository.saveUser(UserData(user.username))
                actions.onSuccessAction(call, response)
            },
            onFailureAction = actions.onFailureAction
        ).toCallback()
}