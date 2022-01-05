package com.example.domain.models

data class PaymentData(
    val sender: UserData,
    val transactions: List<TransactionData>,
) {
    data class TransactionData(
        val receiver: UserData,
        val sun: Float,
    )
}