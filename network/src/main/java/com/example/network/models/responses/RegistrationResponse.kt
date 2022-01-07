package com.example.network.models.responses

import com.example.domain.models.auth.RegistrationResult

data class RegistrationResponse(
    val status: RegistrationResult,
)