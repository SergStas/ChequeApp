package com.example.network.converters.serializers

import com.example.domain.models.auth.RegistrationParams
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

internal object RegistrationDataSerializer: JsonSerializer<RegistrationParams> {
    override fun serialize(
        src: RegistrationParams?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement =
        JsonObject().apply {
            addProperty("username", src!!.username)
            addProperty("telegramUsername", src.telegramUsername)
        }
}