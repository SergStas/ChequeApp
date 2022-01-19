package com.example.chequeapp.models.newevent

import com.example.domain.models.SessionData

data class PositionDataElement(
    val id: Int,
    var position: SessionData.PositionData,
    var msg: String?,
) {
    companion object {
        fun withNoMessage(id: Int, position: SessionData.PositionData) =
            PositionDataElement(id, position, null)
    }
}