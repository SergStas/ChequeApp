package com.example.network.converters.deserializers.models

import com.example.domain.models.UserData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object UserDataDeserializer: JsonDeserializer<UserData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): UserData =
        UserData(
            name = (json as JsonObject)["name"].asString,
        )
}