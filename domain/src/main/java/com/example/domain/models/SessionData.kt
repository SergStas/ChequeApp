package com.example.domain.models

data class SessionData(
    val name: String,
    val date: String,
    val participants: List<UserData>,
    val receipts: List<ReceiptData>,
) {
    data class ReceiptData(
        val payer: UserData,
        val positions: List<PositionData>,
    )

    data class PositionData(
        val name: String,
        val price: Float,
        val parts: List<PartData>,
    )

    data class PartData(
        val user: UserData,
        val part: Float,
    )
}



