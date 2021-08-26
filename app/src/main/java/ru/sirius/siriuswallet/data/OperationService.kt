package ru.sirius.siriuswallet.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.ApplicationConstants
import ru.sirius.siriuswallet.data.local.repository.OperationCacheRepository
import ru.sirius.siriuswallet.model.Operation

class OperationService(
    private val operationNetworkRepository: OperationRepository,
    private val operationLocalRepository: OperationCacheRepository
) {
    suspend fun loadOperations(accountId: Int, onCacheLoaded: (Response<List<Operation>>) -> Unit, onNetworkLoaded: (Response<List<Operation>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val localResp = operationLocalRepository.getOperationsByAccountId(accountId)
            onCacheLoaded(localResp)
            if (ApplicationConstants.TEST_DELAY > 0) {
                delay(ApplicationConstants.TEST_DELAY)
            }
            val networkResp = operationNetworkRepository.getOperationsByAccountId(accountId)
            onNetworkLoaded(networkResp)
            if (networkResp is Response.Success) {
                operationLocalRepository.deleteOperationsWithCategories()
                networkResp.responseBody.forEach {
                    operationLocalRepository.insertOperation(it)
                }
            }
        }
    }

    suspend fun createOperation(operation: Operation): Response<Int> = withContext(Dispatchers.IO) {
        val networkResponse = operationNetworkRepository.insertOperation(operation)
        if (networkResponse is Response.Success) {
            val operationWithGeneratedId = operation.copy(id = networkResponse.responseBody)
            operationLocalRepository.insertOperation(operationWithGeneratedId)
        }
        return@withContext networkResponse
    }
}