package com.gmail.lyohakasianik.solveittest.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetProvider {
    private var api: Api? = null

    fun provideGson() = GsonBuilder().create()

    fun provideOkHttp() = OkHttpClient.Builder().build()

    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun provideApi(retrofit: Retrofit): Api {
        if (api == null) {
            api = retrofit.create(
                Api::class.java
            )
        }
        return api!!
    }
}