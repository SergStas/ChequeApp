package com.example.chequeapp.domain.usecases.history

import com.example.chequeapp.domain.models.UserData
import com.example.chequeapp.domain.models.common.NetworkRequestActions
import com.example.chequeapp.domain.network.AbstractApiAccessObject
import com.example.chequeapp.domain.network.models.responses.HistoryResponse

class HistoryRequestUseCase(
    private val apiAccessObject: AbstractApiAccessObject,
) {
    fun execute(userData: UserData, actions: NetworkRequestActions<HistoryResponse>) {
        actions.preloadAction()
        val call = apiAccessObject.cApi.getHistory(username = userData.name)
        call.enqueue(actions.toCallback())
    }
}