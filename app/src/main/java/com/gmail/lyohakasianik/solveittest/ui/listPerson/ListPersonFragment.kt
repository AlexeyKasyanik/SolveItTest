package com.gmail.lyohakasianik.solveittest.ui.listPerson

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.di.viewModelFactory.PersonListViewModelFactory
import com.gmail.lyohakasianik.solveittest.mvvm.MVVMState
import com.gmail.lyohakasianik.solveittest.mvvm.PersonListViewModel
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonForDb
import com.gmail.lyohakasianik.solveittest.ui.listPerson.adapterListPersons.PersonAdapter
import com.gmail.lyohakasianik.solveittest.utils.SPECIALTY_ID
import com.gmail.lyohakasianik.solveittest.utils.ToastUtils
import com.gmail.lyohakasianik.solveittest.utils.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.list_person_fragment.*
import javax.inject.Inject

@ExperimentalStdlibApi
class ListPersonFragment : Fragment(), PersonAdapter.OnClickListener {

    @Inject
    lateinit var factory: PersonListViewModelFactory
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
    private var listenerTouch: ListenerStartPersonFragment? = null

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
        initDagger()
        initViewModel()
        initAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDagger() {
        App.appComponent.inject(this)
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
        viewModel = ViewModelProviders.of(this, factory).get(PersonListViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, observable)
        viewModel.getPersonsForSpecialty(idSpecialty.toLong())
    }

    override fun onItemClick(item: PersonForDb) {
        listenerTouch?.startPersonFragment(item.id)
    }


    interface ListenerStartPersonFragment {
        fun startPersonFragment(personId: Long)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerStartPersonFragment) listenerTouch = context
    }

    override fun onDetach() {
        super.onDetach()
        listenerTouch = null
    }
}