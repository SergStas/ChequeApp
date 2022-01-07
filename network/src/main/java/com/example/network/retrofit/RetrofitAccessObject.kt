package com.example.network.retrofit

import com.example.domain.models.PaymentData
import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import com.example.domain.network.models.RegistrationData
import com.example.domain.network.models.responses.*
import com.example.network.converters.deserializers.models.*
import com.example.network.converters.deserializers.responses.*
import com.example.network.converters.serializers.models.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitAccessObject {
    private var converterFactory: GsonConverterFactory? = null
    private var retrofit: Retrofit? = null

    private val cConverterFactory: GsonConverterFactory
        get() = converterFactory ?: run {
            converterFactory = GsonConverterFactory.create(
                GsonBuilder()
                    .apply {
                        addSerializers(this)
                        addDeserializers(this)
                    }.create()
            )
            converterFactory!!
        }

    fun getClient(baseUrl: String): Retrofit =
        retrofit ?: run {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(cConverterFactory)
                .build()
            retrofit!!
        }

    private fun addSerializers(builder: GsonBuilder): GsonBuilder =
        builder.apply {
            registerTypeAdapter(SessionData.PartData::class.java, PartDataSerializer)
            registerTypeAdapter(SessionData.PositionData::class.java, PositionDataSerializer)
            registerTypeAdapter(SessionData.ReceiptData::class.java, ReceiptDataSerializer)
            registerTypeAdapter(RegistrationData::class.java, RegistrationDataSerializer)
            registerTypeAdapter(SessionData::class.java, SessionDataSerializer)
            registerTypeAdapter(UserData::class.java, UserDataSerializer)
        }

    private fun addDeserializers(builder: GsonBuilder): GsonBuilder =
        builder.apply {
            registerTypeAdapter(SessionData.PartData::class.java, PartDataDeserializer)
            registerTypeAdapter(PaymentData::class.java, PaymentDataDeserializer)
            registerTypeAdapter(SessionData.PositionData::class.java, PositionDataDeserializer)
            registerTypeAdapter(SessionData.ReceiptData::class.java, ReceiptDataDeserializer)
            registerTypeAdapter(SessionData::class.java, SessionDataDeserializer)
            registerTypeAdapter(PaymentData.TransactionData::class.java, TransactionDataDeserializer)
            registerTypeAdapter(UserData::class.java, UserDataDeserializer)
            registerTypeAdapter(AllUsersResponse::class.java, AllUsersResponseDeserializer)
            registerTypeAdapter(CalculateResultResponse::class.java, CalculationResultResponseDeserializer)
            registerTypeAdapter(HistoryResponse::class.java, HistoryResponseDeserializer)
            registerTypeAdapter(LoginResponse::class.java, LoginResponseDeserializer)
            registerTypeAdapter(RegistrationResponse::class.java, RegistrationResponseDeserializer)
        }
}