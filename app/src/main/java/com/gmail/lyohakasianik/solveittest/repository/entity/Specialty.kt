package com.gmail.lyohakasianik.solveittest.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Specialty(
    @SerializedName("specialty_id")
    val specialtyId: Long,
    @SerializedName("name")
    val specialtyName: String
) : Parcelable