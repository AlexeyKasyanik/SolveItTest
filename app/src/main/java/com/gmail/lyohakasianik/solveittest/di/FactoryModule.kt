package com.gmail.lyohakasianik.solveittest.di

import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.di.viewModelFactory.PersonListViewModelFactory
import com.gmail.lyohakasianik.solveittest.di.viewModelFactory.PersonViewModelFactory
import com.gmail.lyohakasianik.solveittest.di.viewModelFactory.SpecialtyViewModelFactory
import com.gmail.lyohakasianik.solveittest.repository.ResponseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FactoryModule {

    @JvmStatic
    @Provides
    @Singleton
    fun createPersonListViewModelFactory(database: PersonResponseDao) =
        PersonListViewModelFactory(database)

    @JvmStatic
    @Provides
    @Singleton
    fun createPersonViewModelFactory(database: PersonResponseDao) = PersonViewModelFactory(database)

    @JvmStatic
    @Provides
    @Singleton
    fun createSpecialtyViewModelFactory(
        database: PersonResponseDao,
        provideResponse: ResponseRepository
    ) = SpecialtyViewModelFactory(database, provideResponse)
}