package com.gmail.lyohakasianik.solveittest.utils

import android.annotation.SuppressLint
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.app.App
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
    private val wrongFormat = App.instance.resources.getString(R.string.error_format)

    @SuppressLint("SimpleDateFormat")
    fun getDate(date: String): String {
        return when (date.split("-").first().length) {
            4 -> simpleDateFormat.format(SimpleDateFormat("yyyy-MM-dd").parse(date)) + " г."
            2 -> simpleDateFormat.format(SimpleDateFormat("dd-MM-yyyy").parse(date)) + " г."
            else -> wrongFormat
        }
    }

    fun getAge(date: String): String {
        return try {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = Date().time - simpleDateFormat.parse(date).time
            (calendar.get(Calendar.YEAR) - 1970).toString()
        } catch (e: Exception){
            wrongFormat
        }
    }
}