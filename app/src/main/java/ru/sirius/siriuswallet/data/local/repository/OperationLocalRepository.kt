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

    // TODO make this version of method working (now there's a problem: all operations get ID of category)
    override suspend fun getOperationsByAccountId(accountId: Int): Response<List<Operation>> {
        return Response.Success(db.categoryWithOperationsDao().getOperationsByAccountId(accountId).map { it.toOperation() })
    }

//    override suspend fun getOperationsByAccountId(accountId: Int): Response<List<Operation>> {
//        return Response.Success(
//            db.categoryWithOperationsDao().getCategoriesWithOperationsByUserId(accountId)
//                .flatMap { cat -> cat.operations.map { op -> op.toOperation(cat.category) } })
//    }

    override suspend fun insertOperation(operation: Operation): Response<Int> {
        db.categoryWithOperationsDao().insertOperationWithCategory(
            operation.toOperationEntity(),
            operation.operationCategory.toCategoryEntity()
        )
        return Response.Success(operation.id)
    }

}