package com.example.domain.models

data class EventData(
    val sessionData: SessionData,
    val payments: List<PaymentData>,
)