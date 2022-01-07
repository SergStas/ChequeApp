package com.example.network.converters.serializers.models

import com.example.domain.network.models.RegistrationData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

internal object RegistrationDataSerializer: JsonSerializer<RegistrationData> {
    override fun serialize(
        src: RegistrationData?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement =
        JsonObject().apply {
            addProperty("username", src!!.username)
            addProperty("telegramUsername", src.telegramUsername)
        }
}