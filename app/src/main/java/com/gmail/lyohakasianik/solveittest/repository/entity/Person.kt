package com.gmail.lyohakasianik.solveittest.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    @SerializedName("f_name")
    val firstName: String,
    @SerializedName("l_name")
    val lastName: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("avatr_url")
    val avatarUrl: String,
    @SerializedName("specialty")
    val specialty: List<Specialty>
) : Parcelable