package com.gmail.lyohakasianik.solveittest.repository

import com.gmail.lyohakasianik.solveittest.repository.entity.retrofit.Response
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("65gb/static/raw/master/testTask.json")
    fun getResponsePerson(): Single<Response>
}