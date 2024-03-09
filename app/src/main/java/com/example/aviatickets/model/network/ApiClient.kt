package com.example.aviatickets.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/estharossa/fake-api-demo/offer_list")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * think about performing network request
     */
}