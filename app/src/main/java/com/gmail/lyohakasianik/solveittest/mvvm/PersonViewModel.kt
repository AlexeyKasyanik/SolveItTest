package com.gmail.lyohakasianik.solveittest.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.solveittest.app.App
import com.gmail.lyohakasianik.solveittest.repository.providePersonResponseRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PersonViewModel : ViewModel() {
    private var disposable: Disposable? = null
    private var dataBase = App.instance.getDatabase().getPersonResponseDao()
    val state: MutableLiveData<MVVMState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MVVMState>()
    }

    init {
        providePersonResponseRepository()
        loadResponsePerson()
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

    fun getPersonsForSpecialty(idSpecialty: Long) {
        disposable = Observable.fromCallable { dataBase.getPersonForSpecialty(idSpecialty) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state.value = MVVMState.DataPersonForSpecialty(it)
            }, {
                state.value = MVVMState.Error(it)
            })
    }

    fun getPersonInform(idPerson: Long) {
        disposable = Observable.fromCallable { dataBase.getInformPerson(idPerson) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state.value = MVVMState.DataPerson(it)
            }, {
                state.value = MVVMState.Error(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}