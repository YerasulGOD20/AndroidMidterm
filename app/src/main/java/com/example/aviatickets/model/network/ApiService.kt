package com.example.aviatickets.model.network

import com.example.aviatickets.model.entity.Offer
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://my-json-server.typicode.com/estharossa/fake-api-demo/offer_list") // replace "offer-list" with your actual endpoint
    fun getOfferList(): Call<List<Offer>>
}
