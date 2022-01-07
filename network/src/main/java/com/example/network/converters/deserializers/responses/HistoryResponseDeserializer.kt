package com.example.network.converters.deserializers.responses

import com.example.domain.models.PaymentData
import com.example.domain.models.SessionData
import com.example.domain.network.models.responses.HistoryResponse
import com.google.gson.*
import java.lang.reflect.Type

internal object HistoryResponseDeserializer: JsonDeserializer<HistoryResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): HistoryResponse =
        HistoryResponse(
            results = (json as JsonArray).map { j ->
                HistoryResponse.HistoryItem(
                    sessionData = context!!.deserialize((j as JsonObject)["session"], SessionData::class.java),
                    payments = (j["payments"] as JsonArray).map { p ->
                        context.deserialize(p, PaymentData::class.java)
                    },
                )
            }
        )
}