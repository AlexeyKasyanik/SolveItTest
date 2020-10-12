package com.gmail.lyohakasianik.solveittest.repository.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialty")
data class SpecialtyForDb (
    @PrimaryKey
    val specialtyId: Long,
    val specialtyName: String
)