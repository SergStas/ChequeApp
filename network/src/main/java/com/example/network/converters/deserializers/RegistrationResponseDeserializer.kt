package com.example.network.converters.deserializers

import com.example.domain.models.auth.RegistrationResult
import com.example.network.models.responses.RegistrationResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object RegistrationResponseDeserializer: JsonDeserializer<RegistrationResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext,
    ): RegistrationResponse =
        RegistrationResponse(
            status = RegistrationResult.values()
                .first { v -> v.code == (json as JsonObject)["status"].asInt },
        )
}