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

    override suspend fun getCategoryById(id: Int): Category {
        // TODO what if category is not found?
        return db.categoryWithOperationsDao().getCategoryById(id).toCategory()
    }

    override suspend fun getCategoriesByTypeAndAccountId(categoryType: CategoryType, accountId: Int): Response<List<Category>> {
        return Response.Success(
            db.categoryWithOperationsDao().getCategoriesByTypeAndAccountId(categoryType.toCategoryDBType(), accountId)
                .map { it.toCategory() })
    }

    override suspend fun addCategory(category: Category): Response<Category> {
        db.categoryWithOperationsDao().insertCategoryIfNotExists(category.toCategoryEntity())
        return Response.Success(category)
    }
}