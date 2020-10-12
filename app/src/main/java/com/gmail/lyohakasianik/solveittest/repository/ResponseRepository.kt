package com.gmail.lyohakasianik.solveittest.repository

import com.gmail.lyohakasianik.solveittest.repository.entity.retrofit.Response
import io.reactivex.Single

interface ResponseRepository {
    fun getResponsePerson(): Single<Response>
}