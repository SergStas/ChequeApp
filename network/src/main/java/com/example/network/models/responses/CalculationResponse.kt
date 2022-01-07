package com.example.network.models.responses

import com.example.domain.models.PaymentData

data class CalculationResponse(
    val payments: List<PaymentData>,
)
