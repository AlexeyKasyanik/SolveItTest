package com.gmail.lyohakasianik.solveittest.repository

import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.repository.NetProvider.provideGson
import com.gmail.lyohakasianik.solveittest.repository.NetProvider.provideOkHttp
import com.gmail.lyohakasianik.solveittest.utils.BASE_URL

fun providePersonResponseRepository() = PersonRepositoryRemote(
    NetProvider.provideApi(
        NetProvider.provideRetrofit(
            BASE_URL,
            provideOkHttp(),
            provideGson()
        )
    ),
    App.instance.getDatabase().getPersonResponseDao()
)