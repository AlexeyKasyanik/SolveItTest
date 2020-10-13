package com.gmail.lyohakasianik.solveittest.utils

import java.util.*

object NameUtils {

    @ExperimentalStdlibApi
    fun getNameWithFirstLetterUpperCase(name: String) =
        name.toLowerCase(Locale.getDefault()).capitalize(Locale.getDefault())
}