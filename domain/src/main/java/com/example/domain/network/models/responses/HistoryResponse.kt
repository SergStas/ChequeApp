package com.example.domain.network.models.responses

import com.example.domain.models.PaymentData
import com.example.domain.models.SessionData

data class HistoryResponse(
    val results: List<HistoryItem>,
) {
    data class HistoryItem(
        val sessionData: SessionData,
        val payments: List<PaymentData>,
    )
}
