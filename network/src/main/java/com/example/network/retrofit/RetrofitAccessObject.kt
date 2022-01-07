package com.example.network.retrofit

import com.example.domain.models.EventData
import com.example.domain.models.PaymentData
import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import com.example.domain.models.auth.RegistrationParams
import com.example.network.converters.deserializers.*
import com.example.network.converters.deserializers.PartDataDeserializer
import com.example.network.converters.deserializers.PaymentDataDeserializer
import com.example.network.converters.deserializers.SessionDataDeserializer
import com.example.network.converters.deserializers.TransactionDataDeserializer
import com.example.network.converters.deserializers.UserDataDeserializer
import com.example.network.converters.serializers.*
import com.example.network.converters.serializers.PartDataSerializer
import com.example.network.converters.serializers.PositionDataSerializer
import com.example.network.converters.serializers.RegistrationDataSerializer
import com.example.network.converters.serializers.SessionDataSerializer
import com.example.network.converters.serializers.UserDataSerializer
import com.example.network.models.responses.*
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
            registerTypeAdapter(EventData::class.java, EventDataDeserializer)
            registerTypeAdapter(SessionData.PartData::class.java, PartDataSerializer)
            registerTypeAdapter(SessionData.PositionData::class.java, PositionDataSerializer)
            registerTypeAdapter(SessionData.ReceiptData::class.java, ReceiptDataSerializer)
            registerTypeAdapter(RegistrationParams::class.java, RegistrationDataSerializer)
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
            registerTypeAdapter(CalculationResponse::class.java, CalculationResponseDeserializer)
            registerTypeAdapter(HistoryResponse::class.java, HistoryResponseDeserializer)
            registerTypeAdapter(LoginResponse::class.java, LoginResponseDeserializer)
            registerTypeAdapter(RegistrationResponse::class.java, RegistrationResponseDeserializer)
        }
}