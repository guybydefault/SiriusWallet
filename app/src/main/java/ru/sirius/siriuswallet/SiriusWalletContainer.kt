package ru.sirius.siriuswallet

import android.annotation.SuppressLint
import ru.sirius.siriuswallet.dao.OperationService
import ru.sirius.siriuswallet.dao.network.repository.CategoryNetworkRepository
import ru.sirius.siriuswallet.operations.OperationsViewModel

class SiriusWalletContainer {
    @SuppressLint("NewApi")
    val operationsService = OperationService()
    val operationsViewModel = OperationsViewModel(this)
}