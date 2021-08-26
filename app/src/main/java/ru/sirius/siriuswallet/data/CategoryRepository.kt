package ru.sirius.siriuswallet.data

import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

interface CategoryRepository {
    suspend fun getCategoriesByTypeAndAccountId(categoryType: CategoryType, accountId: Int): Response<List<Category>>
    suspend fun addCategory(category: Category): Response<Category>
}