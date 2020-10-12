package com.gmail.lyohakasianik.solveittest.ui

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
import com.gmail.lyohakasianik.solveittest.mvvm.PersonViewModel
import com.gmail.lyohakasianik.solveittest.repository.entity.db.SpecialtyForDb
import com.gmail.lyohakasianik.solveittest.ui.adapter.SpecialtyAdapter
import com.gmail.lyohakasianik.solveittest.utils.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.list_specialty_fragment.*
import kotlinx.android.synthetic.main.start_activity.*

class ListSpecialtyFragment : Fragment(), SpecialtyAdapter.OnClickListener {

    private lateinit var viewModel: PersonViewModel
    private lateinit var adapterSpecialty: SpecialtyAdapter
    private val observable = Observer<MVVMState> {
        when (it) {
            is MVVMState.DataSpecialty -> {
                progressBar.visibility = View.GONE
                adapterSpecialty.updateListCurrency(it.listSpecialtyForDb)
            }
            is MVVMState.Error -> {
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_specialty_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, observable)
    }

    private fun initAdapter() {
        listSpecialtyRecyclerView.layoutManager = LinearLayoutManager(App.instance)
        listSpecialtyRecyclerView.setHasFixedSize(true)
        listSpecialtyRecyclerView.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimension(R.dimen.size_space_for_item_decoration)
            .toInt()))
        adapterSpecialty = SpecialtyAdapter(listOf(), this)
        listSpecialtyRecyclerView.adapter = adapterSpecialty
    }

    override fun onItemClick(item: SpecialtyForDb) {

    }
}