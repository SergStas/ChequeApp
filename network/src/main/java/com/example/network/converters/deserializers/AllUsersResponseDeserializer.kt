package com.example.network.converters.deserializers

import com.example.domain.models.UserData
import com.example.network.models.responses.AllUsersResponse
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

internal object AllUsersResponseDeserializer: JsonDeserializer<AllUsersResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): AllUsersResponse =
        AllUsersResponse(
            users = (json as JsonArray).map { j -> context!!.deserialize(j, UserData::class.java) },
        )
}