package com.example.domain.network.models.responses

import com.example.domain.models.PaymentData

data class CalculateResultResponse(
    val payments: List<PaymentData>,
)
