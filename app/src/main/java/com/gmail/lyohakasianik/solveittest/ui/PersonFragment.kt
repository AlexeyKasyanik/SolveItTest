package com.gmail.lyohakasianik.solveittest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.mvvm.MVVMState
import com.gmail.lyohakasianik.solveittest.mvvm.PersonViewModel
import com.gmail.lyohakasianik.solveittest.utils.DateUtils.getAge
import com.gmail.lyohakasianik.solveittest.utils.DateUtils.getDate
import com.gmail.lyohakasianik.solveittest.utils.PERSON_ID
import com.gmail.lyohakasianik.solveittest.utils.ToastUtils
import kotlinx.android.synthetic.main.person_fragment.*

class PersonFragment : Fragment() {

    private lateinit var viewModel: PersonViewModel
    private var idPerson = ""

    private val observable = Observer<MVVMState> {
        when (it) {
            is MVVMState.DataPerson -> {
                firstNameTextView.text = it.personForDb.firstName
                lastNameTextView.text = it.personForDb.lastName
                dateTextView.text = getDate(it.personForDb.birthday ?: "")
                ageTextView.text = getAge(dateTextView.text.toString())
                imageView.load(it.personForDb.avatarUrl)
            }
            is MVVMState.Error -> {
                ToastUtils.showToast(App.instance, R.string.error_download)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idPerson = this.arguments?.getString(PERSON_ID).toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, observable)
        viewModel.getPersonInform(idPerson.toLong())
    }
}