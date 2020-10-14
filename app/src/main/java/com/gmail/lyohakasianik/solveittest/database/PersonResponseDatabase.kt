package com.gmail.lyohakasianik.solveittest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonAndSpecialty
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonForDb
import com.gmail.lyohakasianik.solveittest.repository.entity.db.SpecialtyForDb

@Database(
    entities = [PersonAndSpecialty::class, PersonForDb::class, SpecialtyForDb::class],
    version = 1, exportSchema = false
)
abstract class PersonResponseDatabase : RoomDatabase() {
    abstract fun getPersonResponseDao(): PersonResponseDao
}
