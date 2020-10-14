package com.gmail.lyohakasianik.solveittest.di.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.mvvm.SpecialtyViewModel
import com.gmail.lyohakasianik.solveittest.repository.ResponseRepository

@Suppress("UNCHECKED_CAST")
class SpecialtyViewModelFactory(
    private val database: PersonResponseDao,
    private val provideResponse: ResponseRepository
) : ViewModelProvider.Factory {
    @ExperimentalStdlibApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpecialtyViewModel(database, provideResponse) as T
    }
}