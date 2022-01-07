package com.example.network

import com.example.domain.models.SessionData
import com.example.domain.models.auth.RegistrationParams
import com.example.network.models.responses.*
import retrofit2.Call
import retrofit2.http.*

interface IChequeAppApi {
    @GET("api/Calculate")
    fun calculateCheque(
        @Body sessionData: SessionData,
    ): Call<CalculationResponse>

    @POST("api/User")
    fun register(
        @Body registrationData: RegistrationParams,
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