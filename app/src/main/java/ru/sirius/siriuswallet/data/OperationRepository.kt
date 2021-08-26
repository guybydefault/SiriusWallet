package ru.sirius.siriuswallet.data

import ru.sirius.siriuswallet.model.Operation

interface OperationRepository {
    suspend fun getOperationsByAccountId(accountId: Int): Response<List<Operation>>
    suspend fun insertOperation(operation: Operation): Response<Int>
}