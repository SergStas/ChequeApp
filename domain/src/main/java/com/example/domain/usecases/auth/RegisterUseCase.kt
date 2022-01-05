package com.example.domain.usecases.auth

import com.example.domain.models.UserData
import com.example.domain.models.common.NetworkRequestActions
import com.example.domain.network.AbstractApiAccessObject
import com.example.domain.network.models.RegistrationData
import com.example.domain.network.models.responses.RegistrationResponse
import com.example.domain.repository.IUserRepository
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