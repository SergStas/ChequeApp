package com.example.chequeapp.domain.network.models.responses

import com.example.chequeapp.domain.models.UserData

data class AllUsersResponse(
    val users: List<UserData>,
)
