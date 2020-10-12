package com.gmail.lyohakasianik.solveittest.mvvm

import com.gmail.lyohakasianik.solveittest.repository.entity.Response

sealed class MVVMState {
    class Data(val responsePerson: Response): MVVMState()
    class Error(val throwable: Throwable): MVVMState()
}