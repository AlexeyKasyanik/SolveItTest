package com.gmail.lyohakasianik.solveittest.repository.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.lyohakasianik.solveittest.utils.RESPONSE_PERSON_TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = RESPONSE_PERSON_TABLE_NAME)
class ResponseForDb(
    @PrimaryKey
    val qwe: Int = 1,
    val response: Response
) : Parcelable