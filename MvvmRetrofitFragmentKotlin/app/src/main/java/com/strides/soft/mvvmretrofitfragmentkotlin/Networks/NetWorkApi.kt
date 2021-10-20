package com.chethan.demoproject


import com.strides.soft.mvvmretrofitfragmentkotlin.Models.PatientHistoryModel
import retrofit2.Call
import retrofit2.http.GET

interface NetWorkApi{

    @GET("GET_CL_FILL_PT_HISTORY")
    fun getPatients(): Call<List<PatientHistoryModel>>

}