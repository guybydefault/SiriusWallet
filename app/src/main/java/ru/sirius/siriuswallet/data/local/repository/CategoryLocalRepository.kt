package ru.sirius.siriuswallet.data.local.repository

import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.data.local.Database
import ru.sirius.siriuswallet.data.toCategory
import ru.sirius.siriuswallet.data.toCategoryDBType
import ru.sirius.siriuswallet.data.toCategoryEntity
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class CategoryLocalRepository(private val db: Database) : CategoryCacheRepository {
    override suspend fun deleteCategoriesByType(categoryType: CategoryType) {
        db.categoryWithOperationsDao().deleteCategoriesByType(categoryType.toCategoryDBType())
    }

    override suspend fun getCategoriesByType(categoryType: CategoryType): Response<List<Category>> {
        return Response.Success(
            db.categoryWithOperationsDao().getCategoriesByType(categoryType.toCategoryDBType())
                .map { it.toCategory() })
    }

    override suspend fun addCategory(category: Category): Response<Category> {
        db.categoryWithOperationsDao().insertCategory(category.toCategoryEntity())
        return Response.Success(category)
    }
}