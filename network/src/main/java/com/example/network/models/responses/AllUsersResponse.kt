package com.example.network.models.responses

import com.example.domain.models.UserData

data class AllUsersResponse(
    val users: List<UserData>,
)
