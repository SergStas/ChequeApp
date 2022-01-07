package com.example.domain.usecases.calculate

import com.example.domain.models.SessionData
import com.example.domain.models.common.NetworkRequestActions
import com.example.domain.network.IApiProvider
import com.example.domain.network.models.responses.CalculateResultResponse

class CalculateResultUseCase(
    private val apiProvider: IApiProvider
) {
    fun execute(sessionData: SessionData, actions: NetworkRequestActions<CalculateResultResponse>) {
        actions.preloadAction()
        val call = apiProvider.getApi().calculateCheque(sessionData)
        call.enqueue(actions.toCallback())
    }
}