package com.example.chequeapp.domain.usecases.auth

import com.example.chequeapp.domain.models.UserData
import com.example.chequeapp.domain.repository.IUserRepository

class GetLoggedInUserUseCase(
    private val userRepository: IUserRepository
) {
    fun execute(): UserData? =
        userRepository.getUser()
}