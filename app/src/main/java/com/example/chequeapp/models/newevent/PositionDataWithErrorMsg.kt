package com.example.chequeapp.models.newevent

import com.example.domain.models.SessionData

data class PositionDataWithErrorMsg(
    var position: SessionData.PositionData,
    var msg: String?,
) {
    companion object {
        fun withNoMessage(position: SessionData.PositionData) =
            PositionDataWithErrorMsg(position, null)
    }
}