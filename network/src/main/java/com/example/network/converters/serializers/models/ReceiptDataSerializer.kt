package com.example.network.converters.serializers.models

import com.example.domain.models.SessionData
import com.google.gson.*
import java.lang.reflect.Type

internal object ReceiptDataSerializer: JsonSerializer<SessionData.ReceiptData> {
    override fun serialize(
        src: SessionData.ReceiptData?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement =
        JsonObject().apply {
            add("payer", context!!.serialize(src!!.payer))
            add("positions",
                JsonArray().apply {
                    src.positions.forEach { p -> add(context.serialize(p)) }
                },
            )
        }
}