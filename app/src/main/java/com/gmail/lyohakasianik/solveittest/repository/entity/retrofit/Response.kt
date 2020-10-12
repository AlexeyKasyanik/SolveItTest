package com.gmail.lyohakasianik.solveittest.repository.entity.retrofit

import com.gmail.lyohakasianik.solveittest.repository.entity.retrofit.Person
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("response")
    val listPerson: List<Person>
)