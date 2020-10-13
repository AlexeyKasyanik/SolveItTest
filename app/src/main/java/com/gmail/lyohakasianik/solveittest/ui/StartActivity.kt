package com.gmail.lyohakasianik.solveittest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.ui.specialty.ListSpecialtyFragment


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.containerForFragment,
                ListSpecialtyFragment()
            )
            transaction.commit()
        }
    }
}