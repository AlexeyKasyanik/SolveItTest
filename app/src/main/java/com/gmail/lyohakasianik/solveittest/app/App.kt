package com.gmail.lyohakasianik.solveittest.app

import android.app.Application
import com.gmail.lyohakasianik.solveittest.di.AppComponent
import com.gmail.lyohakasianik.solveittest.di.DaggerAppComponent

@ExperimentalStdlibApi
class App : Application() {
    companion object {

        lateinit var appComponent: AppComponent
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        instance = this
    }
}