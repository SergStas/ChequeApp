package com.example.network.converters.deserializers

import com.example.domain.models.PaymentData
import com.example.domain.models.UserData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object PaymentDataDeserializer: JsonDeserializer<PaymentData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): PaymentData =
        PaymentData(
            sender = context!!.deserialize((json as JsonObject)["sender"], UserData::class.java),
            transactions = json["transactions"].asJsonArray
                .map { j -> context.deserialize(j, PaymentData.TransactionData::class.java) },
        )
}