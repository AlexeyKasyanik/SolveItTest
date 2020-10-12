package com.gmail.lyohakasianik.solveittest.app

import android.app.Application
import androidx.room.Room
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDatabase

class App : Application() {
    companion object {
        lateinit var database: PersonResponseDatabase
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        database = Room.databaseBuilder(this, PersonResponseDatabase::class.java, "name")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): PersonResponseDatabase {
        return database
    }
}