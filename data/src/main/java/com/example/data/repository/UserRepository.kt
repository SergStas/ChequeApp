package com.example.data.repository

import com.example.data.models.User
import com.example.data.storage.IDeviceUserStorage
import com.example.domain.models.UserData
import com.example.domain.models.auth.LoginParams
import com.example.domain.models.auth.LoginResult
import com.example.domain.models.auth.RegistrationParams
import com.example.domain.models.auth.RegistrationResult
import com.example.domain.repository.IUserRepository
import com.example.network.IApiProvider

class UserRepository(
    apiProvider: IApiProvider,
    private val userStorage: IDeviceUserStorage,
): IUserRepository {
    private val api = apiProvider.getApi()

    override fun getLoggedInUser(): UserData? =
        userStorage.getUser()?.toUserData()

    override fun loginUser(params: LoginParams): LoginResult =
        try {
            val call = api.login(params.username)
            val response = call.execute()
            val result =
                if (response.code() == 404) LoginResult.UserNotFound
                else LoginResult.Success(user = response.body()!!.userData!!)
            if (result is LoginResult.Success) {
                userStorage.saveUser(User.fromUserData(result.user))
            }
            result
        }
        catch (t: Throwable) { LoginResult.Error(t) }

    override fun register(params: RegistrationParams): RegistrationResult =
        try {
            val result = api.register(params).execute().body()!!.status
            if (result == RegistrationResult.Ok) {
                userStorage.saveUser(User(params.username))
            }
            result
        }
        catch (_: Throwable) { RegistrationResult.Ok }  // FIXME when server starts

    override fun getAllUsers(): List<UserData> =
        try { api.getAllUsers().execute().body()!!.users }
        catch (_: Throwable) { emptyList() }
}