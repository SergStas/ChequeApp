package com.example.network.converters.deserializers.models

import com.example.domain.models.PaymentData
import com.example.domain.models.UserData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object TransactionDataDeserializer: JsonDeserializer<PaymentData.TransactionData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): PaymentData.TransactionData =
        PaymentData.TransactionData(
            receiver = context!!.deserialize((json as JsonObject)["receiver"], UserData::class.java),
            sun = json["sum"].asFloat,
        )
}