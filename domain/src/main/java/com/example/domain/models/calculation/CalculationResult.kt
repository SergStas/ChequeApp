package com.example.domain.models.calculation

import com.example.domain.models.PaymentData

sealed class CalculationResult {
    data class Success(val payments: List<PaymentData>)
}
