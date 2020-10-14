package com.gmail.lyohakasianik.solveittest.di.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.lyohakasianik.solveittest.database.PersonResponseDao
import com.gmail.lyohakasianik.solveittest.mvvm.PersonListViewModel

@Suppress("UNCHECKED_CAST")
class PersonListViewModelFactory(private val database: PersonResponseDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonListViewModel(database) as T
    }
}