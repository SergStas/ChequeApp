package com.example.domain.models

data class SessionData(
    var name: String,
    var date: Long,
    var participants: List<UserData>,
    var receipts: List<ReceiptData>,
) {
    data class ReceiptData(
        val name: String,
        val payer: UserData,
        val positions: List<PositionData>,
    ) {
        companion object {
            fun namedAs(name: String) =
                ReceiptData(
                    name = name,
                    payer = UserData(""),
                    positions = emptyList(),
                )
        }
    }

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



