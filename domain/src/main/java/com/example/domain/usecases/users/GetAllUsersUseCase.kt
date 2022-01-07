package com.example.domain.usecases.users

import com.example.domain.models.common.NetworkRequestActions
import com.example.domain.network.IApiProvider
import com.example.domain.network.models.responses.AllUsersResponse

class GetAllUsersUseCase(
    private val apiProvider: IApiProvider,
) {
    fun execute(actions: NetworkRequestActions<AllUsersResponse>) {
        actions.preloadAction()
        val call = apiProvider.getApi().getAllUsers()
        call.enqueue(actions.toCallback())
    }
}