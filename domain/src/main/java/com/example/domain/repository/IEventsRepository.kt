package com.example.domain.repository

import com.example.domain.models.EventData
import com.example.domain.models.SessionData
import com.example.domain.models.calculation.CalculationResult

interface IEventsRepository {
    fun calculateChequesForEvent(sessionData: SessionData): CalculationResult

    fun getEventsHistory(): List<EventData>
}