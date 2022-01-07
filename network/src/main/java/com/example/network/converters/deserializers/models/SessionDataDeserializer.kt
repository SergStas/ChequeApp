package com.example.network.converters.deserializers.models

import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object SessionDataDeserializer: JsonDeserializer<SessionData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SessionData =
        SessionData(
            name = (json as JsonObject)["name"].asString,
            date = json["date"].asString,
            participants = json["participants"].asJsonArray
                .map { j -> context!!.deserialize(j, UserData::class.java) },
            receipts = json["receipts"].asJsonArray
                .map { j -> context!!.deserialize(j, SessionData.ReceiptData::class.java) },
        )
}