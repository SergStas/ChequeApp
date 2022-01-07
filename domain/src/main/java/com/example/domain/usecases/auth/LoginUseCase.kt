package com.example.domain.usecases.auth

import com.example.domain.repository.IUserRepository
import com.example.domain.models.auth.LoginParams
import com.example.domain.models.auth.LoginResult

class LoginUseCase(
    private val userRepository: IUserRepository,
) {
    fun execute(params: LoginParams): LoginResult =
        userRepository.loginUser(params)
}