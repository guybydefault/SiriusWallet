package ru.sirius.siriuswallet.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.data.local.repository.OperationCacheRepository
import ru.sirius.siriuswallet.model.Operation

class OperationService(
    private val operationNetworkRepository: OperationRepository,
    private val operationLocalRepository: OperationCacheRepository
) {
    suspend fun loadOperations(accountId: Int, onLoad: (Response<List<Operation>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val localResp = operationLocalRepository.getOperationsByAccountId(accountId)
            onLoad(localResp)

            delay(1000) // TODO remove
            val networkResp = operationNetworkRepository.getOperationsByAccountId(accountId)
            if (networkResp is Response.Success) {
                onLoad(networkResp)
                operationLocalRepository.deleteOperationsWithCategories()
                networkResp.responseBody.forEach {
                    operationLocalRepository.insertOperation(it)
                }
            }
        }
    }
}