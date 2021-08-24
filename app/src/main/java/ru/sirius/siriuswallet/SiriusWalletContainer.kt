package ru.sirius.siriuswallet

import ru.sirius.siriuswallet.data.CategoryService
import ru.sirius.siriuswallet.data.OperationService
import ru.sirius.siriuswallet.operations.OperationsViewModel

class SiriusWalletContainer {
    val operationsService = OperationService()
    val categoriesService = CategoryService()
    val operationsViewModel: OperationsViewModel by lazy(LazyThreadSafetyMode.NONE) { OperationsViewModel(this) }
    val categoriesViewModel: CategoriesViewModel by lazy(LazyThreadSafetyMode.NONE) { CategoriesViewModel(this) }
}