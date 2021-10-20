package com.strides.soft.mvvmretrofitkotlinsamplebyak.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.strides.soft.apptunixassignmentkotlin.Models.BreedModel2
import com.strides.soft.apptunixassignmentkotlin.Network.ApiClient
import com.strides.soft.apptunixassignmentkotlin.Network.ApiInterface
import com.strides.soft.mvvmretrofitkotlinsamplebyak.Interfaces.NetworkResponseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedRepository private constructor() {
    private lateinit var mCallback: NetworkResponseCallback
    private var breedList: MutableLiveData<List<BreedModel2>> =
        MutableLiveData<List<BreedModel2>>().apply { value = emptyList() }

    companion object {
        private var mInstance: BreedRepository? = null
        fun getInstance(): BreedRepository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = BreedRepository()
                }
            }
            return mInstance!!
        }
    }



    fun getCountries(callback: NetworkResponseCallback, forceFetch : Boolean): MutableLiveData<List<BreedModel2>> {
        val apiInterface: ApiInterface = ApiClient.getClient()?.create(ApiInterface::class.java)!!
        val call: Call<BreedModel2> = apiInterface.getBreedImage()
        call?.enqueue(object: Callback<BreedModel2> {
            override fun onResponse(call: Call<BreedModel2>, response: Response<BreedModel2>) {
                breedList.value = response.body()
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<BreedModel2>, t: Throwable) {
                breedList.value = emptyList()
                mCallback.onNetworkFailure(t)
            }

        })
        return breedList
    }

}