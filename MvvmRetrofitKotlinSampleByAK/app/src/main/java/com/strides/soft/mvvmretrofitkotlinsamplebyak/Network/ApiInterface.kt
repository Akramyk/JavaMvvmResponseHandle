package com.strides.soft.apptunixassignmentkotlin.Network

import com.strides.soft.apptunixassignmentkotlin.Models.BreedModel2
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("random")
    fun getBreedImage(): Call<BreedModel2>
}