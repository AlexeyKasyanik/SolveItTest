package com.gmail.lyohakasianik.solveittest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import coil.transform.CircleCropTransformation
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.di.viewModelFactory.PersonViewModelFactory
import com.gmail.lyohakasianik.solveittest.mvvm.MVVMState
import com.gmail.lyohakasianik.solveittest.mvvm.PersonViewModel
import com.gmail.lyohakasianik.solveittest.utils.DateUtils.getAge
import com.gmail.lyohakasianik.solveittest.utils.DateUtils.getDate
import com.gmail.lyohakasianik.solveittest.utils.PERSON_ID
import com.gmail.lyohakasianik.solveittest.utils.ToastUtils
import kotlinx.android.synthetic.main.person_fragment.*
import javax.inject.Inject

@ExperimentalStdlibApi
class PersonFragment : Fragment() {

    @Inject
    lateinit var factory: PersonViewModelFactory
    private lateinit var viewModel: PersonViewModel
    private var idPerson = ""


    private val observable = Observer<MVVMState> {
        when (it) {
            is MVVMState.DataPerson -> {
                firstNameTextView.text = it.personForDb.personForDb.firstName
                lastNameTextView.text = it.personForDb.personForDb.lastName
                dateTextView.text = getDate(it.personForDb.personForDb.birthday ?: "")
                ageTextView.text = getAge(dateTextView.text.toString())
                imageView.load(it.personForDb.personForDb.avatarUrl) {
                    transformations(CircleCropTransformation())
                }
                var qwe = ""
                for(i in it.personForDb.listSpecialty) qwe += " ${i.specialtyName}"
                specialtyPersonTextView.text = qwe
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
        initDagger()
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDagger() {
        App.appComponent.inject(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(PersonViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, observable)
        viewModel.getPersonInform(idPerson.toLong())
    }
}