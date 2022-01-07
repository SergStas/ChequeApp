package com.example.domain.usecases.auth

import com.example.domain.models.auth.RegistrationParams
import com.example.domain.models.auth.RegistrationResult
import com.example.domain.repository.IUserRepository

class RegisterUseCase(
    private val userRepository: IUserRepository,
) {
    fun execute(params: RegistrationParams): RegistrationResult =
        userRepository.register(params)
}