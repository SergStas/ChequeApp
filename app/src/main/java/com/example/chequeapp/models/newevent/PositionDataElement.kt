package com.example.chequeapp.models.newevent

import com.example.domain.models.SessionData
import com.example.domain.models.UserData

data class PositionDataElement(
    val id: Int,
    val position: SessionData.PositionData,
    val msg: String?,
    val selectedUserData: UserData?,
)