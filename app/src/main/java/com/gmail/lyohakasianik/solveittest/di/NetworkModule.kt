package com.gmail.lyohakasianik.solveittest.di

import com.gmail.lyohakasianik.solveittest.repository.Api
import com.gmail.lyohakasianik.solveittest.utils.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    @Singleton
    @Named("url")
    fun provideUrl() = BASE_URL

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("url")
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttp() = OkHttpClient.Builder().build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)

}