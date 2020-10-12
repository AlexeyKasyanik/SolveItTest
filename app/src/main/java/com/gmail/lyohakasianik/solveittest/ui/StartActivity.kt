package com.gmail.lyohakasianik.solveittest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.mvvm.MVVMState
import com.gmail.lyohakasianik.solveittest.mvvm.PersonViewModel
import kotlinx.android.synthetic.main.start_activity.*


class StartActivity : AppCompatActivity() {
    val zxc = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        progressBar.setIndeterminateDrawable(DoubleBounce())
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.containerForFragment, ListSpecialtyFragment())
            transaction.commit()
        }
    }
}