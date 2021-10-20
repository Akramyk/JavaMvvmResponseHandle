package com.strides.soft.mvvmretrofitfragmentkotlin.Repositories

import com.chethan.demoproject.NetWorkApi
import com.strides.soft.mvvmretrofitfragmentkotlin.Models.PatientHistoryModel
import retrofit2.Call
import retrofit2.Response


class DataRepository(val netWorkApi: NetWorkApi) {

    fun getPatient(onPatientData: OnPatientData) {
        netWorkApi.getPatients().enqueue(object : retrofit2.Callback<List<PatientHistoryModel>> {
            override fun onResponse(call: Call<List<PatientHistoryModel>>, response: Response<List<PatientHistoryModel>>) {
                onPatientData.onSuccess((response.body() as List<PatientHistoryModel>))
            }

            override fun onFailure(call: Call<List<PatientHistoryModel>>, t: Throwable) {
                onPatientData.onFailure()
            }
        })
    }

    interface OnPatientData {
        fun onSuccess(data: List<PatientHistoryModel>)
        fun onFailure()
    }
}

