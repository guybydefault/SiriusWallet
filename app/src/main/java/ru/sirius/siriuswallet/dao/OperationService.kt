package ru.sirius.siriuswallet.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.dao.network.repository.OperationsNetworkRepository
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime

class OperationService {
    private val operationNetworkRepository = OperationsNetworkRepository()

    val placeholderDataset: List<Operation> = mutableListOf(
        Operation(
            1, LocalDateTime.now(), BigDecimal(-12000), Category(
                1, 1, "Супермаркеты",
                CategoryType.OUTCOME, R.drawable.ic_supermarket
            )
        )
    )


    suspend fun getOperations(
        accountId: Int
    ): Response<List<Operation>> = withContext(Dispatchers.IO) {
        return@withContext operationNetworkRepository.getOperations(accountId)
    }
}