package com.gmail.lyohakasianik.solveittest.repository.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.lyohakasianik.solveittest.utils.RESPONSE_PERSON_TABLE_NAME
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    @PrimaryKey
    @SerializedName("response")
    val listPerson: List<Person>
) : Parcelable