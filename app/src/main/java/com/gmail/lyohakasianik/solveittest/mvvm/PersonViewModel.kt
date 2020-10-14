package com.gmail.lyohakasianik.solveittest.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PersonViewModel @Inject constructor(private val database: PersonResponseDao) : ViewModel() {

    private var disposable: Disposable? = null
    val state: MutableLiveData<MVVMState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MVVMState>()
    }

    fun getPersonInform(idPerson: Long) {
        disposable = Observable.fromCallable { database.getInformPerson(idPerson) }
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