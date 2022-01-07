package com.example.network.converters.deserializers

import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object PartDataDeserializer: JsonDeserializer<SessionData.PartData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SessionData.PartData =
        SessionData.PartData(
            user = context!!.deserialize((json as JsonObject)["user"], UserData::class.java),
            part = json["part"].asFloat,
        )
}