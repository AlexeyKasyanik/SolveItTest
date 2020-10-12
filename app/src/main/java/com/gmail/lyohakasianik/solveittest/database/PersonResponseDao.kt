package com.gmail.lyohakasianik.solveittest.database

import androidx.room.*
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonAndSpecialty
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonForDb
import com.gmail.lyohakasianik.solveittest.repository.entity.db.SpecialtyForDb
import com.gmail.lyohakasianik.solveittest.repository.entity.retrofit.Response

@Dao
abstract class PersonResponseDao {

    @Transaction
    open fun insert(response: Response) {
        clearTable()

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
                    insert(
                        SpecialtyForDb(
                            specialty.specialtyId,
                            specialty.specialtyName
                        )
                    )

                insert(
                    PersonAndSpecialty(
                        personId,
                        specialtyId
                    )
                )
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(specialtyForDb: SpecialtyForDb): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(personForDb: PersonForDb): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(personAndSpecialty: PersonAndSpecialty)

    @Query("DELETE FROM person_and_specialty")
    abstract fun clearTable()

    @Query("SELECT * FROM specialty")
    abstract fun getListSpecialty(): List<SpecialtyForDb>

    @Query("SELECT person.* FROM person_and_specialty, person WHERE person_and_specialty.personId = person.id AND person_and_specialty.specialtyId = :idSpecialty")
    abstract fun getPersonForSpecialty(idSpecialty: Long): List<PersonForDb>

    @Query("SELECT * FROM person WHERE person.id = :idPerson")
    abstract fun getInformPerson(idPerson: Long): PersonForDb

}