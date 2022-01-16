package com.example.domain.usecases.users

import com.example.domain.models.UserData
import com.example.domain.repository.IUserRepository

class GetAllUsersUseCase(
    private val userRepository: IUserRepository,
) {
    fun execute(): List<UserData> =
        listOf(
            UserData("Danila"),
            UserData("Gooseale"),
            UserData("Sasha"),
            UserData("Lesha"),
        )
//        userRepository.getAllUsers()  // TODO: uncomment when server starts
}