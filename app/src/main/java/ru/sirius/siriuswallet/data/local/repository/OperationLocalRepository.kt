package ru.sirius.siriuswallet.data.local.repository

import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.data.local.Database
import ru.sirius.siriuswallet.data.toCategoryEntity
import ru.sirius.siriuswallet.data.toOperation
import ru.sirius.siriuswallet.data.toOperationEntity
import ru.sirius.siriuswallet.model.Operation

class OperationLocalRepository(private val db: Database) : OperationCacheRepository {
    override suspend fun deleteOperationsWithCategories(): Response<Boolean> {
        db.categoryWithOperationsDao().deleteAll()
        return Response.Success(true)
    }

    override suspend fun getOperationsByAccountId(accountId: Int): Response<List<Operation>> {
        return Response.Success(db.categoryWithOperationsDao().getOperationsByUserId(accountId).map { it.toOperation() })
    }

    override suspend fun insertOperation(operation: Operation): Response<Operation> {
        db.categoryWithOperationsDao().insertOperationWithCategory(
                operation.toOperationEntity(),
                operation.operationCategory.toCategoryEntity())
        return Response.Success(operation)
    }

}