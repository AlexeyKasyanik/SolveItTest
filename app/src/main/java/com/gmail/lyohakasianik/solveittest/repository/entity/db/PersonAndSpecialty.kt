package com.gmail.lyohakasianik.solveittest.repository.entity.db

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "specialtyId"], tableName = "person_and_specialty")
class PersonAndSpecialty(
    val personId: Long,
    val specialtyId: Long
)