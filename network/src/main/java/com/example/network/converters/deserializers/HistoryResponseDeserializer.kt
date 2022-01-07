package com.example.network.converters.deserializers

import com.example.domain.models.EventData
import com.example.network.models.responses.HistoryResponse
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

internal object HistoryResponseDeserializer: JsonDeserializer<HistoryResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): HistoryResponse =
        HistoryResponse(
            results = (json as JsonArray).map {
                j -> context!!.deserialize(j, EventData::class.java)
            }
        )
}