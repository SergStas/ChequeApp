package com.example.network.converters.deserializers

import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

internal object SessionDataDeserializer: JsonDeserializer<SessionData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SessionData =
        SessionData(
            name = (json as JsonObject)["name"].asString,
            date = parseDateToLong(token = json["date"].asString),
            participants = json["participants"].asJsonArray
                .map { j -> context!!.deserialize(j, UserData::class.java) },
            receipts = json["receipts"].asJsonArray
                .map { j -> context!!.deserialize(j, SessionData.ReceiptData::class.java) },
        )

    private fun parseDateToLong(token: String): Long =
        SimpleDateFormat("yyyy-mm-dd", Locale("us"))
            .parse(token.split("T")[0])!!.time +
        SimpleDateFormat("hh:mm:ss.SSS", Locale("us"))
            .parse(token.split("T")[1].split("Z")[0])!!.time
}