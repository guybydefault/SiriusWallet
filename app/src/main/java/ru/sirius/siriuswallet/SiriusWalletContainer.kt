package ru.sirius.siriuswallet

import android.annotation.SuppressLint
import ru.sirius.siriuswallet.network.repository.CategoryNetworkRepository
import ru.sirius.siriuswallet.operations.OperationsViewModel

class SiriusWalletContainer {
    @SuppressLint("NewApi")
    val dataRepository = CategoryNetworkRepository()
    val operationsViewModel = OperationsViewModel(this)
}