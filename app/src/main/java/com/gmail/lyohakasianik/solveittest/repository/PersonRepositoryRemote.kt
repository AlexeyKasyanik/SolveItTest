package com.gmail.lyohakasianik.solveittest.repository

import android.util.Log
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.repository.entity.retrofit.Response
import io.reactivex.Single

class PersonRepositoryRemote(
    private val api: Api,
    private val personResponseDao: PersonResponseDao
) : ResponseRepository {
    override fun getResponsePerson(): Single<Response> {
        return api.getResponsePerson().doOnSuccess {
            personResponseDao.insert(it)
        }.onErrorResumeNext {
            Single.error(it)
        }
    }
}