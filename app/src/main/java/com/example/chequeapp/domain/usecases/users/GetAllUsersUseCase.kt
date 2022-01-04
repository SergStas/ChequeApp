package com.example.chequeapp.domain.usecases.users

import com.example.chequeapp.domain.models.common.NetworkRequestActions
import com.example.chequeapp.domain.network.AbstractApiAccessObject
import com.example.chequeapp.domain.network.models.responses.AllUsersResponse

class GetAllUsersUseCase(
    private val apiAccessObject: AbstractApiAccessObject,
) {
    fun execute(actions: NetworkRequestActions<AllUsersResponse>) {
        actions.preloadAction()
        val call = apiAccessObject.cApi.getAllUsers()
        call.enqueue(actions.toCallback())
    }
}