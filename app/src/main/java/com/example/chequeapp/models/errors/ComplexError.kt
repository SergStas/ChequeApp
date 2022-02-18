package com.example.chequeapp.models.errors

class CompositeError {
    private val errors = mutableListOf<Error>()

    val list: List<Error>
        get() = errors.toList()

    val last: Error?
        get() = if (errors.isEmpty()) null else errors.last()

    val isEmpty: Boolean
        get() = errors.isEmpty()

    fun add(error: Error) =
        errors.add(error)

    fun remove(error: Error) =
        errors.remove(error)
}