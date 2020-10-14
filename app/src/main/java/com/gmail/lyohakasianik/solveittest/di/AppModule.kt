package com.gmail.lyohakasianik.solveittest.di

import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.repository.Api
import com.gmail.lyohakasianik.solveittest.repository.PersonRepositoryRemote
import com.gmail.lyohakasianik.solveittest.repository.ResponseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @JvmStatic
    @Provides
    @Singleton
    fun providePersonResponseRepository(api: Api, dataBase: PersonResponseDao): ResponseRepository =
        PersonRepositoryRemote(
            api,
            dataBase
        )
}