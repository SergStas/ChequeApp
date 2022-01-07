package com.example.network.converters.deserializers

import com.example.domain.models.PaymentData
import com.example.network.models.responses.CalculationResponse
import com.google.gson.*
import java.lang.reflect.Type

internal object CalculationResponseDeserializer: JsonDeserializer<CalculationResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): CalculationResponse =
        CalculationResponse(
            payments = (json as JsonArray).map { j -> context!!.deserialize(j, PaymentData::class.java) },
        )
}