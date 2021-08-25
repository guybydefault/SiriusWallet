package ru.sirius.siriuswallet.data.local.repository

import ru.sirius.siriuswallet.data.CategoryRepository
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.data.local.Database
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class CategoryLocalRepository(db: Database) : CategoryRepository {
    override suspend fun getCategoriesByType(categoryType: CategoryType): Response<List<Category>> {
        TODO("Not yet implemented")
    }
}