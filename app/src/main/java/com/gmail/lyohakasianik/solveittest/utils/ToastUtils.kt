package com.gmail.lyohakasianik.solveittest.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {
    fun showToast(context: Context, text: Int) {
        Toast.makeText(context, context.getString(text), Toast.LENGTH_LONG).show()
    }
}
