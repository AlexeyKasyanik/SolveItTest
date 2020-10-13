package com.gmail.lyohakasianik.solveittest.ui.listPerson

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.mvvm.MVVMState
import com.gmail.lyohakasianik.solveittest.mvvm.PersonListViewModel
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonForDb
import com.gmail.lyohakasianik.solveittest.ui.PersonFragment
import com.gmail.lyohakasianik.solveittest.ui.listPerson.adapterListPersons.PersonAdapter
import com.gmail.lyohakasianik.solveittest.utils.PERSON_ID
import com.gmail.lyohakasianik.solveittest.utils.SPECIALTY_ID
import com.gmail.lyohakasianik.solveittest.utils.ToastUtils
import com.gmail.lyohakasianik.solveittest.utils.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.list_person_fragment.*

class ListPersonFragment : Fragment(), PersonAdapter.OnClickListener {

    private lateinit var viewModel: PersonListViewModel
    private var idSpecialty = ""
    private lateinit var adapterPersons: PersonAdapter
    private val observable = Observer<MVVMState> {
        when (it) {
            is MVVMState.DataPersonForSpecialty -> {
                adapterPersons.updateListPersons(it.listPersonForSpecialty)
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
        return inflater.inflate(R.layout.list_person_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idSpecialty = this.arguments?.getString(SPECIALTY_ID).toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        initAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter() {
        listPersonsRecyclerView.layoutManager =
            LinearLayoutManager(App.instance, LinearLayoutManager.VERTICAL, false)
        listPersonsRecyclerView.setHasFixedSize(true)
        listPersonsRecyclerView.addItemDecoration(
            VerticalSpaceItemDecoration(
                resources.getDimension(R.dimen.size_space_for_item_decoration)
                    .toInt()
            )
        )
        adapterPersons = PersonAdapter(listOf(), this)
        listPersonsRecyclerView.adapter = adapterPersons
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PersonListViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, observable)
        viewModel.getPersonsForSpecialty(idSpecialty.toLong())
    }

    override fun onItemClick(item: PersonForDb) {
        val personFragment = PersonFragment()
        val bundle = Bundle()
        bundle.putString(PERSON_ID, item.id.toString())
        personFragment.arguments = bundle
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.containerForFragment,
            personFragment
        ).addToBackStack(null).commit()
    }
}