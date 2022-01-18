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
        var name: String,
        var price: Float,
        var parts: List<PartData>,
    ) {
        fun applyDefaultParts(users: List<UserData>) {
            if (users.isEmpty()) return
            parts = users.map { user ->
                PartData(user, 100f / users.size)
            }
        }
    }

    data class PartData(
        val user: UserData,
        val part: Float,
    )
}



