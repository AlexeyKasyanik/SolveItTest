package com.gmail.lyohakasianik.solveittest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.gmail.lyohakasianik.solveittest.repository.entity.PersonAndSpecialty
import com.gmail.lyohakasianik.solveittest.repository.entity.PersonForDb
import com.gmail.lyohakasianik.solveittest.repository.entity.Response
import com.gmail.lyohakasianik.solveittest.repository.entity.SpecialtyForDb

@Dao
abstract class PersonResponseDao {

    @Transaction
    open fun insert(response: Response) {

        for (person in response.listPerson) {

            val personId = insert(
                PersonForDb(
                    0,
                    person.firstName,
                    person.lastName,
                    person.birthday,
                    person.avatarUrl
                )
            )

            for (specialty in person.specialty) {
                val specialtyId =
                    insert(SpecialtyForDb(0, specialty.specialtyId, specialty.specialtyName))

                insert(PersonAndSpecialty(personId, specialtyId))
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(specialtyForDb: SpecialtyForDb): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(personForDb: PersonForDb): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(personAndSpecialty: PersonAndSpecialty)
}