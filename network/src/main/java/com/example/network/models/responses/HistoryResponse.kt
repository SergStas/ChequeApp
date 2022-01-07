package com.example.network.models.responses

import com.example.domain.models.EventData

data class HistoryResponse(
    val results: List<EventData>,
)