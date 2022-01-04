package com.example.chequeapp.domain.usecases.calculate

import com.example.chequeapp.domain.models.SessionData
import com.example.chequeapp.domain.models.common.NetworkRequestActions
import com.example.chequeapp.domain.network.AbstractApiAccessObject
import com.example.chequeapp.domain.network.models.responses.CalculateResultResponse

class CalculateResultUseCase(
    private val apiAccessObject: AbstractApiAccessObject
) {
    fun execute(sessionData: SessionData, actions: NetworkRequestActions<CalculateResultResponse>) {
        actions.preloadAction()
        val call = apiAccessObject.cApi.calculateCheque(sessionData)
        call.enqueue(actions.toCallback())
    }
}