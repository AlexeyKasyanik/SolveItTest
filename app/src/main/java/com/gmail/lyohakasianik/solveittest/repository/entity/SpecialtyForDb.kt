package com.gmail.lyohakasianik.solveittest.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpecialtyForDb (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val specialtyId: Long,
    val specialtyName: String
)