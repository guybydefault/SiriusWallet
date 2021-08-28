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
    suspend fun getCategories(
        categoryType: CategoryType,
        userId: Int,
        accountId: Int,
        onLoad: (Response<List<Category>>, Boolean) -> Unit
    ): Response<List<Category>> =
        withContext(Dispatchers.IO) {
            val localResp = categoryLocalRepository.getCategoriesByTypeAndUserId(categoryType, userId, accountId)
            onLoad(localResp, false)
            if (ApplicationConstants.TEST_DELAY > 0) {
                delay(ApplicationConstants.TEST_DELAY)
            }
            val networkResp = categoryNetworkRepository.getCategoriesByTypeAndUserId(categoryType, userId, accountId)
            onLoad(networkResp, true)
            if (networkResp is Response.Success) {
                networkResp.responseBody.forEach {
//                    categoryLocalRepository.deleteCategoriesByType(categoryType) TODO (this version is working supposing that we cannot delete categories right now)
                    networkResp.responseBody.forEach { categoryLocalRepository.addCategory(it) }
                }
            }
            return@withContext networkResp
        }

    suspend fun getCachedCategoryById(categoryId: Int): Category = withContext(Dispatchers.IO) {
        println("LOOKING UP CATEGORY ${categoryId}")
        return@withContext categoryLocalRepository.getCategoryById(categoryId)
    }
}