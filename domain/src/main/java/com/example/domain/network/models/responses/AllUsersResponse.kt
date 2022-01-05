package com.example.domain.network.models.responses

import com.example.domain.models.UserData

data class AllUsersResponse(
    val users: List<UserData>,
)
