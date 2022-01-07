package com.example.domain.usecases.calculate

import com.example.domain.models.calculation.CalculationParams
import com.example.domain.models.calculation.CalculationResult
import com.example.domain.repository.IEventsRepository

class CalculationUseCase(
    private val calculationRepository: IEventsRepository,
) {
    fun execute(params: CalculationParams): CalculationResult =
        calculationRepository.calculateChequesForEvent(params.sessionData)
}