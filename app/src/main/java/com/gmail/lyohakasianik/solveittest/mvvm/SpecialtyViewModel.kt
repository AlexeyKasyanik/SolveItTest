package com.gmail.lyohakasianik.solveittest.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.repository.providePersonResponseRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@ExperimentalStdlibApi
class SpecialtyViewModel : ViewModel() {
    private var disposable: Disposable? = null
    private var dataBase = App.instance.getDatabase().getPersonResponseDao()
    val state: MutableLiveData<MVVMState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MVVMState>()
    }

    init {
        loadData()
    }

    fun loadData() {
        disposable = providePersonResponseRepository().getResponsePerson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadResponsePerson()
            },{})
    }

    fun loadResponsePerson() {
        disposable = Observable.fromCallable { dataBase.getListSpecialty() }
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