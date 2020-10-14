package com.gmail.lyohakasianik.solveittest.mvvm

import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonForDb
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonWitchSpecialty
import com.gmail.lyohakasianik.solveittest.repository.entity.db.SpecialtyForDb

sealed class MVVMState {
    class DataSpecialty(val listSpecialtyForDb: List<SpecialtyForDb>) : MVVMState()
    class DataPersonForSpecialty(val listPersonForSpecialty: List<PersonForDb>) : MVVMState()
    class DataPerson(val personForDb: PersonWitchSpecialty) : MVVMState()

    class Error(val throwable: Throwable) : MVVMState()
}