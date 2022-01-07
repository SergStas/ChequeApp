package com.example.network

import com.example.network.retrofit.RetrofitAccessObject

class ApiProvider: IApiProvider {
    companion object {
        private const val API_URL = ""
    }

    override fun getApi(): IChequeAppApi =
        RetrofitAccessObject.getClient(API_URL)
            .create(IChequeAppApi::class.java)
}