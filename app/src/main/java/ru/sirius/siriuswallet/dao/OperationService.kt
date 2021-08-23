package ru.sirius.siriuswallet.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.dao.network.repository.OperationsNetworkRepository
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime

class OperationService {
    val operationNetworkRepository = OperationsNetworkRepository()

    val placeholderDataset: List<Operation> = mutableListOf(
        Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now(), BigDecimal(-12000),
        ),
        Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now(), BigDecimal(130000),
        ), Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(1), BigDecimal(130000),
        ), Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now().minusDays(2), BigDecimal(-12000),
        ), Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(2), BigDecimal(130000)
        ),
        Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(4), BigDecimal(130000)
        ),
        Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now().minusDays(4), BigDecimal(-12000)
        )
    )


    suspend fun getOperations(
        accountId: Int
    ): Response<List<Operation>> = withContext(Dispatchers.IO) {
        return@withContext operationNetworkRepository.getOperations(accountId)
    }
}