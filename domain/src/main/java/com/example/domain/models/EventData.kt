package com.example.domain.models

data class EventData(
    val sessionData: SessionData,
    val payments: List<PaymentData>,
) {
    companion object {
        fun empty() =
            EventData(
                sessionData = SessionData(
                    name = "",
                    date = 0,
                    participants = emptyList(),
                    receipts = emptyList(),
                ),
                payments = emptyList(),
            )
    }
}