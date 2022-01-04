package com.example.chequeapp.domain.network.models.responses

import com.example.chequeapp.domain.models.PaymentData

data class CalculateResultResponse(
    val payments: List<PaymentData>,
)
