package com.example.chequeapp.domain.network

import com.example.chequeapp.domain.models.SessionData
import com.example.chequeapp.domain.network.models.*
import com.example.chequeapp.domain.network.models.responses.*
import retrofit2.Call
import retrofit2.http.*

interface IChequeAppApi {
    @GET("api/Calculate")
    fun calculateCheque(
        @Body sessionData: SessionData,
    ): Call<CalculateResultResponse>

    @POST("api/User")
    fun register(
        @Body registrationData: RegistrationData,
    ): Call<RegistrationResponse>

    @GET("api/User/{username}")
    fun login(
        @Path("username") username: String,
    ): Call<LoginResponse>

    @GET("api/User")
    fun getAllUsers(): Call<AllUsersResponse>

    @GET("api/Session")
    fun getHistory(
        @Query("username") username: String,
    ): Call<HistoryResponse>
}