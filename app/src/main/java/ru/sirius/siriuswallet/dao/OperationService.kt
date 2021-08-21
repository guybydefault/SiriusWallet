package ru.sirius.siriuswallet.dao

import ru.sirius.siriuswallet.dao.network.repository.OperationsNetworkRepository
import ru.sirius.siriuswallet.model.Operation

class OperationService {
    val operationNetworkRepository = OperationsNetworkRepository()

    suspend fun getOperations(
        accountId: Int
    ): Response<List<Operation>> {
        return operationNetworkRepository.getOperations(accountId)
    }
}