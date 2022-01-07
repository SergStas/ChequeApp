package com.example.network.converters.deserializers

import com.example.domain.models.UserData
import com.example.network.models.responses.LoginResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

internal object LoginResponseDeserializer: JsonDeserializer<LoginResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): LoginResponse =
        LoginResponse(
            userData = if (json == null) null else context!!.deserialize<UserData>(json, UserData::class.java),
        )
}