package ru.sirius.siriuswallet

import android.annotation.SuppressLint
import ru.sirius.siriuswallet.operations.OperationsViewModel

class SiriusWalletContainer {
    @SuppressLint("NewApi")
    val dataRepository = DataRepository()
    val operationsViewModel = OperationsViewModel(this)
}