package com.example.chequeapp.models.errors

sealed class Error {
    abstract val message: String
}

data class NewReceiptError(override val message: String): Error()