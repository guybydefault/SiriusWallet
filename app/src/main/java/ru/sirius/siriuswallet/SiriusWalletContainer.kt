package ru.sirius.siriuswallet

import android.content.Context
import androidx.room.Room
import ru.sirius.siriuswallet.data.CategoryService
import ru.sirius.siriuswallet.data.OperationService
import ru.sirius.siriuswallet.data.local.Database
import ru.sirius.siriuswallet.data.local.repository.CategoryLocalRepository
import ru.sirius.siriuswallet.data.local.repository.OperationLocalRepository
import ru.sirius.siriuswallet.data.network.repository.CategoryNetworkRepository
import ru.sirius.siriuswallet.data.network.repository.OperationNetworkRepository
import ru.sirius.siriuswallet.operations.OperationsViewModel
import ru.sirius.siriuswallet.view.transition.EditOperationViewModel

class SiriusWalletContainer(applicationContext: Context) {
    val database = Room.databaseBuilder(applicationContext, Database::class.java, "sirius-wallet").build()

    val categoryLocalRepository = CategoryLocalRepository(database)
    val categoryNetworkRepository = CategoryNetworkRepository()
    val operationLocalRepository = OperationLocalRepository(database)
    val operationNetworkRepository = OperationNetworkRepository()

    val operationsService = OperationService(operationNetworkRepository, operationLocalRepository)
    val categoriesService = CategoryService(categoryNetworkRepository, categoryLocalRepository)

    val operationsViewModel: OperationsViewModel by lazy(LazyThreadSafetyMode.NONE) { OperationsViewModel(this) }
    val editOperationViewModel: EditOperationViewModel by lazy(LazyThreadSafetyMode.NONE) { EditOperationViewModel(this) }
}