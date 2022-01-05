package com.example.domain.network.models.responses

data class RegistrationResponse(
    val status: RegistrationStatus,
) {
    enum class RegistrationStatus(val code: Int) {
        Ok(0),
        UsernameIsOccupied(1),
        UnknownError(99),
    }
}