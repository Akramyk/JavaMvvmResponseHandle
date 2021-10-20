package com.chethan.demoproject

import com.strides.soft.mvvmretrofitfragmentkotlin.Repositories.DataRepository
import com.strides.soft.mvvmretrofitfragmentkotlin.ViewModels.PatientViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {

    single { DataRepository(get()) }

    single { createWebService() }

    viewModel { PatientViewModel(get()) }

}

fun createWebService(): NetWorkApi {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        //.baseUrl("http://mobcategories.s3-website-eu-west-1.amazonaws.com")
        .baseUrl("http://60.254.61.193/QA-ST-MA-SVC/api/ST_SVC/")
        .build()

    return retrofit.create(NetWorkApi::class.java)
}