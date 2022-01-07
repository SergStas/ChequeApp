package com.example.network.converters.deserializers.responses

import com.example.domain.models.PaymentData
import com.example.domain.network.models.responses.CalculateResultResponse
import com.google.gson.*
import java.lang.reflect.Type

internal object CalculationResultResponseDeserializer: JsonDeserializer<CalculateResultResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): CalculateResultResponse =
        CalculateResultResponse(
            payments = (json as JsonArray).map { j -> context!!.deserialize(j, PaymentData::class.java) },
        )
}