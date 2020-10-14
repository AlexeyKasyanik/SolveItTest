package com.gmail.lyohakasianik.solveittest.di.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.mvvm.PersonViewModel

@Suppress("UNCHECKED_CAST")
class PersonViewModelFactory(private val database: PersonResponseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonViewModel(database) as T
    }
}