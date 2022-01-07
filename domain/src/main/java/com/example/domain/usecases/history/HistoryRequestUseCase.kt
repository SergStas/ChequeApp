package com.example.domain.usecases.history

import com.example.domain.models.UserData
import com.example.domain.models.common.NetworkRequestActions
import com.example.domain.network.IApiProvider
import com.example.domain.network.models.responses.HistoryResponse

class HistoryRequestUseCase(
    private val apiProvider: IApiProvider,
) {
    fun execute(userData: UserData, actions: NetworkRequestActions<HistoryResponse>) {
        actions.preloadAction()
        val call = apiProvider.getApi().getHistory(username = userData.name)
        call.enqueue(actions.toCallback())
    }
}