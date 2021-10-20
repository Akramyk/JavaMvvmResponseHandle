package com.strides.soft.apptunixassignmentkotlin.ViewModels

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noob.apps.mvvmcountries.utils.NetworkHelper
import com.strides.soft.apptunixassignmentkotlin.Models.BreedModel2
import com.strides.soft.apptunixassignmentkotlin.Network.ApiClient
import com.strides.soft.apptunixassignmentkotlin.Network.ApiInterface
import com.strides.soft.mvvmretrofitkotlinsamplebyak.Interfaces.NetworkResponseCallback
import com.strides.soft.mvvmretrofitkotlinsamplebyak.Repositories.BreedRepository
import com.strides.soft.mvvmretrofitkotlinsamplebyak.TimerStatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimerViewModel(private val app: Application) : AndroidViewModel(app) {
    private var mList: MutableLiveData<List<BreedModel2>> =
        MutableLiveData<List<BreedModel2>>().apply { value = emptyList() }
    val mShowProgressBar = MutableLiveData(true)
    val mShowNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    val mShowApiError = MutableLiveData<String>()
    private var mRepository = BreedRepository.getInstance()

    fun fetchCountriesFromServer(forceFetch: Boolean): MutableLiveData<List<BreedModel2>> {
        if (NetworkHelper.isOnline(app.baseContext)) {
            mShowProgressBar.value = true
            mList = mRepository.getCountries(object : NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {
                    mShowApiError.value = th.message
                }

                override fun onNetworkSuccess() {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }
        return mList
    }

    fun onRefreshClicked(view: View) {
        fetchCountriesFromServer(true)
    }



}