package com.example.network.converters.deserializers

import com.example.domain.models.EventData
import com.example.domain.models.PaymentData
import com.example.domain.models.SessionData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

internal object EventDataDeserializer: JsonDeserializer<EventData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): EventData =
        EventData(
            sessionData = context!!.deserialize((json as JsonObject)["session"], SessionData::class.java),
            payments = context.deserialize(json["payments"], PaymentData::class.java),
        )
}