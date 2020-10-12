package com.gmail.lyohakasianik.solveittest.repository

import com.gmail.lyohakasianik.solveittest.repository.entity.Response
import com.gmail.lyohakasianik.solveittest.repository.entity.ResponseForDb
import io.reactivex.Single

interface ResponseRepository {
    fun getResponsePerson(): Single<ResponseForDb>
}