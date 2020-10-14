package com.gmail.lyohakasianik.solveittest.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.repository.ResponseRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ExperimentalStdlibApi
class SpecialtyViewModel @Inject constructor(
    private val database: PersonResponseDao,
    private val provideResponse: ResponseRepository
) :
    ViewModel() {
    private var disposable: Disposable? = null
    val state: MutableLiveData<MVVMState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MVVMState>()
    }

    init {
        loadData()
    }

    fun loadData() {
        disposable = provideResponse.getResponsePerson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadResponsePerson()
            }, {})
    }

    fun loadResponsePerson() {
        disposable = Observable.fromCallable { database.getListSpecialty() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state.value = MVVMState.DataSpecialty(it)
            }, {
                state.value = MVVMState.Error(it)
            })

    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}