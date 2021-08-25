package ru.sirius.siriuswallet.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.data.local.repository.CategoryLocalRepository
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class CategoryService(
    private val categoryNetworkRepository: CategoryRepository,
    private val categoryLocalRepository: CategoryLocalRepository
) {
    suspend fun getCategories(categoryType: CategoryType): Response<List<Category>> = withContext(Dispatchers.IO) {
        val response = categoryNetworkRepository.getCategoriesByType(categoryType)
        if (response is Response.Success) {
            response.responseBody.forEach {
                // TODO update categories
//                db.categoryWithOperationsDao().insertCategory(
//                    it.toCategoryEntity()
//                )
            }
        }
        return@withContext response
    }
}