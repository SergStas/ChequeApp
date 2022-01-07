package com.example.network.converters.serializers

import com.example.domain.models.SessionData
import com.google.gson.*
import java.lang.reflect.Type

internal object PositionDataSerializer: JsonSerializer<SessionData.PositionData> {
    override fun serialize(
        src: SessionData.PositionData?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement =
        JsonObject().apply {
            addProperty("name", src!!.name)
            addProperty("price", src.price)
            add("parts",
                JsonArray().apply {
                    src.parts.forEach { p -> add(context!!.serialize(p)) }
                },
            )
        }
}