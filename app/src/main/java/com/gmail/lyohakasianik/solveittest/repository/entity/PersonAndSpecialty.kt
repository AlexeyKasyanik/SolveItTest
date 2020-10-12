package com.gmail.lyohakasianik.solveittest.repository.entity

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "specialtyId"])
class PersonAndSpecialty(
    val personId: Long,
    val specialtyId: Long
)