package com.gmail.lyohakasianik.solveittest.ui.specialty

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.di.viewModelFactory.SpecialtyViewModelFactory
import com.gmail.lyohakasianik.solveittest.mvvm.MVVMState
import com.gmail.lyohakasianik.solveittest.mvvm.SpecialtyViewModel
import com.gmail.lyohakasianik.solveittest.repository.entity.db.SpecialtyForDb
import com.gmail.lyohakasianik.solveittest.ui.specialty.adapterSpecialty.SpecialtyAdapter
import com.gmail.lyohakasianik.solveittest.utils.ToastUtils.showToast
import com.gmail.lyohakasianik.solveittest.utils.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.list_specialty_fragment.*
import javax.inject.Inject

@ExperimentalStdlibApi
class ListSpecialtyFragment : Fragment(), SpecialtyAdapter.OnClickListener {

    @Inject
    lateinit var factory: SpecialtyViewModelFactory
    private lateinit var viewModel: SpecialtyViewModel
    private lateinit var adapterSpecialty: SpecialtyAdapter
    private val observable = Observer<MVVMState> {
        when (it) {
            is MVVMState.DataSpecialty -> {
                progressBar.visibility = View.GONE
                adapterSpecialty.updateListCurrency(it.listSpecialtyForDb)
            }
            is MVVMState.Error -> {
                showToast(App.instance, R.string.error_download)
                progressBar.visibility = View.GONE
            }
        }
    }
    private var listenerTouch: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_specialty_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar.setIndeterminateDrawable(DoubleBounce())
        initDagger()
        initViewModel()
        initAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDagger() {
        App.appComponent.inject(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SpecialtyViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, observable)
    }

    private fun initAdapter() {
        listSpecialtyRecyclerView.layoutManager =
            LinearLayoutManager(App.instance, LinearLayoutManager.VERTICAL, false)
        listSpecialtyRecyclerView.setHasFixedSize(true)
        listSpecialtyRecyclerView.addItemDecoration(
            VerticalSpaceItemDecoration(
                resources.getDimension(R.dimen.size_space_for_item_decoration)
                    .toInt()
            )
        )
        adapterSpecialty = SpecialtyAdapter(listOf(), this)
        listSpecialtyRecyclerView.adapter = adapterSpecialty
    }

    override fun onItemClick(item: SpecialtyForDb) {
        listenerTouch?.startListPersonFragment(item.specialtyId)
    }

    interface Listener {
        fun startListPersonFragment(specialtyId: Long)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) listenerTouch = context
    }

    override fun onDetach() {
        super.onDetach()
        listenerTouch = null
    }
}