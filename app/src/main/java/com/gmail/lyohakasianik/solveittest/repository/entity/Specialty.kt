package com.gmail.lyohakasianik.solveittest.repository.entity

import com.google.gson.annotations.SerializedName

data class Specialty(
    @SerializedName("specialty_id")
    val specialtyId: Long,
    @SerializedName("name")
    val specialtyName: String
)