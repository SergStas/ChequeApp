package com.example.domain.usecases.auth

import com.example.domain.models.UserData
import com.example.domain.repository.IUserRepository

class GetLoggedInUserUseCase(
    private val userRepository: IUserRepository
) {
    fun execute(): UserData? =
        userRepository.getUser()
}