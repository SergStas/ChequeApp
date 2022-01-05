package com.example.domain.usecases.users

import com.example.domain.models.common.NetworkRequestActions
import com.example.domain.network.AbstractApiAccessObject
import com.example.domain.network.models.responses.AllUsersResponse

class GetAllUsersUseCase(
    private val apiAccessObject: AbstractApiAccessObject,
) {
    fun execute(actions: NetworkRequestActions<AllUsersResponse>) {
        actions.preloadAction()
        val call = apiAccessObject.cApi.getAllUsers()
        call.enqueue(actions.toCallback())
    }
}