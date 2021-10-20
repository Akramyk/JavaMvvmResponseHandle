package com.strides.soft.mvvmretrofitfragmentkotlin.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.strides.soft.mvvmretrofitfragmentkotlin.Models.PatientHistoryModel
import com.strides.soft.mvvmretrofitfragmentkotlin.Repositories.DataRepository
import org.koin.standalone.KoinComponent

class PatientViewModel(val dataRepository : DataRepository) : ViewModel(), KoinComponent{
    val listOfPatients = MutableLiveData<List<PatientHistoryModel>>()

    init {
        listOfPatients.value = listOf()
    }

    fun getPatients(){
        dataRepository.getPatient(object : DataRepository.OnPatientData{
            override fun onSuccess(data: List<PatientHistoryModel>) {
                Log.d("AK", data.toString());
                listOfPatients.value =data
            }

            override fun onFailure() {
                //REQUEST FAILED
                Log.d("AK", "Failed");
            }

        })
    }
}