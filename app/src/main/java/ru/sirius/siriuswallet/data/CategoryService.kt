package ru.sirius.siriuswallet.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.ApplicationConstants
import ru.sirius.siriuswallet.data.local.repository.CategoryLocalRepository
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class CategoryService(
    private val categoryNetworkRepository: CategoryRepository,
    private val categoryLocalRepository: CategoryLocalRepository
) {
    suspend fun getCategories(categoryType: CategoryType, accountId: Int, onLoad: (Response<List<Category>>, Boolean) -> Unit): Response<List<Category>> =
        withContext(Dispatchers.IO) {
            val localResp = categoryLocalRepository.getCategoriesByTypeAndAccountId(categoryType, accountId)
            onLoad(localResp, false)
            if (ApplicationConstants.TEST_DELAY > 0) {
                delay(ApplicationConstants.TEST_DELAY)
            }
            val networkResp = categoryNetworkRepository.getCategoriesByTypeAndAccountId(categoryType, accountId)
            onLoad(networkResp, true)
            if (networkResp is Response.Success) {
                networkResp.responseBody.forEach {
                    categoryLocalRepository.deleteCategoriesByType(categoryType)
                    networkResp.responseBody.forEach { categoryLocalRepository.addCategory(it) }
                }
            }
            return@withContext networkResp
        }

    suspend fun getCachedCategoryById(categoryId: Int): Category = withContext(Dispatchers.IO) {
        return@withContext categoryLocalRepository.getCategoryById(categoryId)
    }
}