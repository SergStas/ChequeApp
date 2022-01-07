package com.example.network.converters.serializers.models

import com.example.domain.models.SessionData
import com.google.gson.*
import java.lang.reflect.Type

internal object PartDataSerializer: JsonSerializer<SessionData.PartData> {
    override fun serialize(
        src: SessionData.PartData?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement =
        JsonObject().apply {
            add("user", context!!.serialize(src!!.user))
            addProperty("part", src.part)
        }
}