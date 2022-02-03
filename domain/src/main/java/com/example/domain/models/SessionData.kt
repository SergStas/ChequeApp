package com.example.domain.models

data class SessionData(
    val name: String,
    val date: Long,
    val participants: List<UserData>,
    val receipts: List<ReceiptData>,
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
    ) {
        fun withDefaultParts(users: List<UserData>): PositionData =
            copy(
                parts =
                if (users.isNotEmpty()) {
                    val map = users.map { user -> PartData(user, 100f / users.size) }.toMutableList()
                    for (i in map.indices){
                        if (map.sumOf { p -> p.part.toInt() } == 100) break
                        map[i] = map[i].copy(part = map[i].part + 1)
                    }
                    map
                } else {
                    emptyList()
                },
            )

        fun withChangedPart(userName: String, newValue: Float): PositionData {
            if (!parts.map{ p -> p.user.name }.contains(userName)) return copy()
            val newParts = parts.toMutableList().apply {
                val old = first { p -> p.user.name == userName }
                val index = indexOf(old)
                set(index, old.copy(part = newValue))
            }
            return copy(parts = newParts)
        }
    }

    data class PartData(
        val user: UserData,
        val part: Float,
    )
}



