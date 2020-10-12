package com.gmail.lyohakasianik.solveittest.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.solveittest.repository.providePersonResponseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PersonViewModel : ViewModel() {
    private var disposable: Disposable? = null
    val state: MutableLiveData<MVVMState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MVVMState>()
    }

    init {
        loadResponsePerson()
    }

    private fun loadResponsePerson() {
        disposable = providePersonResponseRepository().getResponsePerson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state.value = MVVMState.Data(it)
            }, {
                state.value = MVVMState.Error(it)
            })

    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}