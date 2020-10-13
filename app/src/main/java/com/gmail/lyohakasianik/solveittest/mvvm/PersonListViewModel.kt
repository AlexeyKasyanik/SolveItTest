package com.gmail.lyohakasianik.solveittest.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.solveittest.app.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PersonListViewModel: ViewModel() {

    private var disposable: Disposable? = null
    private var dataBase = App.instance.getDatabase().getPersonResponseDao()
    val state: MutableLiveData<MVVMState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MVVMState>()
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

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}