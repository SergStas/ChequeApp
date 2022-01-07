package com.example.domain.usecases.history

import com.example.domain.models.EventData
import com.example.domain.repository.IEventsRepository

class HistoryRequestUseCase(
    private val eventsRepository: IEventsRepository,
) {
    fun execute(): List<EventData> =
        eventsRepository.getEventsHistory()
}