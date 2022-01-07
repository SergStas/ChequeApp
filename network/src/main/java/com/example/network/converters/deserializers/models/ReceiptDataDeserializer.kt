package com.example.network.converters.deserializers.models

import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object ReceiptDataDeserializer: JsonDeserializer<SessionData.ReceiptData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SessionData.ReceiptData =
        SessionData.ReceiptData(
            payer = context!!.deserialize((json as JsonObject)["payer"], UserData::class.java),
            positions = json["positions"].asJsonArray
                .map { j -> context.deserialize(j, SessionData.PositionData::class.java) }
        )
}