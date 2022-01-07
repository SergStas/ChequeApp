package com.example.domain.models.auth

enum class RegistrationResult(val code: Int) {
    Ok(0),
    UsernameIsOccupied(1),
    UnknownError(99),
}