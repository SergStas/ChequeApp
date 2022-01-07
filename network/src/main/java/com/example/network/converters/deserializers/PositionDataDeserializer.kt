package com.example.network.converters.deserializers

import com.example.domain.models.SessionData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object PositionDataDeserializer: JsonDeserializer<SessionData.PositionData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SessionData.PositionData =
        SessionData.PositionData(
            name = (json as JsonObject)["name"].asString,
            price = json.get("price").asFloat,
            parts = json.getAsJsonArray("parts")
                .map { j -> context!!.deserialize(j, SessionData.PartData::class.java) },
        )
}