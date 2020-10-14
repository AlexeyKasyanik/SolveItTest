package com.gmail.lyohakasianik.solveittest.di

import android.content.Context
import androidx.room.Room
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideDataBase(context: Context) =
        Room.databaseBuilder(context, PersonResponseDatabase::class.java, "name.db")
            .fallbackToDestructiveMigration()
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideDao(database: PersonResponseDatabase) = database.getPersonResponseDao()
}