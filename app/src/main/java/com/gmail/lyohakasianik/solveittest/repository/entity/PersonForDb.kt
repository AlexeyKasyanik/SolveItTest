package com.gmail.lyohakasianik.solveittest.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonForDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val birthday: String?,
    val avatarUrl: String?
)