package com.gmail.lyohakasianik.solveittest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.lyohakasianik.solveittest.repository.entity.Response

@Database(entities = [Response::class], version = 1)
abstract class PersonResponseDatabase : RoomDatabase() {
    abstract fun getPersonResponseDao(): PersonResponseDao
}