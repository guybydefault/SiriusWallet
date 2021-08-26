package ru.sirius.siriuswallet.data.local.repository

import ru.sirius.siriuswallet.data.CategoryRepository
import ru.sirius.siriuswallet.model.CategoryType

interface CategoryCacheRepository : CategoryRepository {
    suspend fun deleteCategoriesByType(categoryType: CategoryType)
}