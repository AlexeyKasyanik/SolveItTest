package com.gmail.lyohakasianik.solveittest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.mvvm.MVVMState
import com.gmail.lyohakasianik.solveittest.mvvm.PersonViewModel
import kotlinx.android.synthetic.main.start_activity.*


class StartActivity : AppCompatActivity() {
    private lateinit var viewModel: PersonViewModel

    private val observable = Observer<MVVMState> {
        when (it) {
            is MVVMState.Data -> {
                progressBar.visibility = View.GONE
                Log.e("QQQ", it.responsePerson.toString() + " 09090")
            }
            is MVVMState.Error -> {
                Log.e("QQQ", it.toString() + "0000000")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        viewModel.state.observe(this, observable)
    }
}