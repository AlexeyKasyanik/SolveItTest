package com.gmail.lyohakasianik.solveittest.repository

import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.repository.entity.Response
import io.reactivex.Single

class PersonRepositoryRemote(
    private val api: Api,
    private val personResponseDao: PersonResponseDao
) : ResponseRepository {
    override fun getResponsePerson(): Single<Response> {
        return api.getResponsePerson().doOnSuccess {
            personResponseDao.insert(it)
        }.onErrorResumeNext {
            if (personResponseDao.get() == Response(listOf())) Single.error(it)
            else Single.just(personResponseDao.get())
        }
    }
}