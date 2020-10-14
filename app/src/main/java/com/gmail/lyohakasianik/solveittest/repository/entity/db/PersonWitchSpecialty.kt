package com.gmail.lyohakasianik.solveittest.repository.entity.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class PersonWitchSpecialty(
    @Embedded
    val personForDb: PersonForDb,

    @Relation(
        associateBy = Junction(
            PersonAndSpecialty::class,
            parentColumn = "personId",
            entityColumn = "specialtyId"
        ), parentColumn = "id", entityColumn = "specialtyId"
    )
    val listSpecialty: List<SpecialtyForDb>
)