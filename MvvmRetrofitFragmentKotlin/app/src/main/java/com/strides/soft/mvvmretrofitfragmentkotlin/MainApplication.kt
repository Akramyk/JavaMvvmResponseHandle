package com.strides.soft.mvvmretrofitfragmentkotlin

import android.app.Application
import com.chethan.demoproject.mainModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this,
            listOf(mainModule),
            loadPropertiesFromFile = true)
    }
}