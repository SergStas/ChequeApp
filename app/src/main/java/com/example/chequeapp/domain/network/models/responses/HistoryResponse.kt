package com.example.chequeapp.domain.network.models.responses

import com.example.chequeapp.domain.models.PaymentData
import com.example.chequeapp.domain.models.SessionData

data class HistoryResponse(
    val results: List<HistoryItem>,
) {
    data class HistoryItem(
        val sessionData: SessionData,
        val payments: List<PaymentData>,
    )
}
