package com.example.chequeapp.domain.models.common

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class NetworkRequestActions<TResponse> (
    val preloadAction: () -> Unit,
    val onSuccessAction: (Call<TResponse>, Response<TResponse>) -> Unit,
    val onFailureAction: (Call<TResponse>, Throwable) -> Unit,
) {
    fun toCallback(): Callback<TResponse> =
        object : Callback<TResponse> {
            override fun onResponse(
                call: Call<TResponse>,
                response: Response<TResponse>,
            ) = onSuccessAction(call, response)

            override fun onFailure(call: Call<TResponse>, t: Throwable) =
                onFailureAction(call, t)
        }
}