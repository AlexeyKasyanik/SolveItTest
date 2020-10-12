package com.gmail.lyohakasianik.solveittest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.lyohakasianik.solveittest.repository.entity.PersonAndSpecialty
import com.gmail.lyohakasianik.solveittest.repository.entity.PersonForDb
import com.gmail.lyohakasianik.solveittest.repository.entity.Response
import com.gmail.lyohakasianik.solveittest.repository.entity.SpecialtyForDb

@Database(
    entities = [PersonAndSpecialty::class, PersonForDb::class, SpecialtyForDb::class],
    version = 1
)
abstract class PersonResponseDatabase : RoomDatabase() {
    abstract fun getPersonResponseDao(): PersonResponseDao
}
