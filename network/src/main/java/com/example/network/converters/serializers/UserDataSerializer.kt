package com.example.network.converters.serializers

import com.example.domain.models.UserData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

internal object UserDataSerializer: JsonSerializer<UserData> {
    override fun serialize(
        src: UserData?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement =
        JsonObject().apply {
            addProperty("name", src!!.name)
        }
}