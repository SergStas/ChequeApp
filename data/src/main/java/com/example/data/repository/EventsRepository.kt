package com.example.data.repository

import com.example.domain.models.EventData
import com.example.domain.models.SessionData
import com.example.domain.models.calculation.CalculationResult
import com.example.domain.repository.IEventsRepository
import com.example.domain.repository.IUserRepository
import com.example.network.IApiProvider

class EventsRepository(
    apiProvider: IApiProvider,
    private val userRepository: IUserRepository
): IEventsRepository {
    private val api = apiProvider.getApi()

    override fun calculateChequesForEvent(sessionData: SessionData): CalculationResult =
        try {
            CalculationResult.Success(
                api.calculateCheque(sessionData)
                    .execute()
                    .body()!!
                    .payments
            )
        }
        catch (t: Throwable) {
            CalculationResult.Error(t)
        }

    override fun getEventsHistory(): List<EventData> =
        try {
            api.getHistory(username = userRepository.getLoggedInUser()!!.name)
                .execute()
                .body()!!
                .results
        }
        catch (_: Throwable) {
            emptyList()
        }
}