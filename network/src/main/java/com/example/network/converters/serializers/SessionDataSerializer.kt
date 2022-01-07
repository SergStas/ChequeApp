package com.example.network.converters.serializers

import com.example.domain.models.SessionData
import com.google.gson.*
import java.lang.reflect.Type

internal object SessionDataSerializer: JsonSerializer<SessionData> {
    override fun serialize(
        src: SessionData?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement =
        JsonObject().apply {
            addProperty("name", src!!.name)
            addProperty("date", src.date)
            add("participants",
                JsonArray().apply {
                    src.participants.forEach { p -> context!!.serialize(p) }
                },
            )
            add("receipts",
                JsonArray().apply {
                  src.receipts.forEach { r -> context!!.serialize(r)}
                },
            )
        }
}