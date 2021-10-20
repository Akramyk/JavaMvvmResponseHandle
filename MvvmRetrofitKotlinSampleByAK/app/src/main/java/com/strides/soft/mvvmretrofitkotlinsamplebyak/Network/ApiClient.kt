package com.strides.soft.apptunixassignmentkotlin.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClient {
    companion object{
        val BASE_URL = "https://dog.ceo/api/breeds/image/"
        var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            retrofit = null
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit
        }
    }
}