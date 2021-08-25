package ru.sirius.siriuswallet.data.local.repository

import ru.sirius.siriuswallet.data.OperationRepository
import ru.sirius.siriuswallet.data.Response

interface OperationCacheRepository : OperationRepository {
    suspend fun deleteOperationsWithCategories(): Response<Boolean>
}