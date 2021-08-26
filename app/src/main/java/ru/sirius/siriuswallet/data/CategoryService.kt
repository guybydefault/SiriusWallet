package ru.sirius.siriuswallet.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.data.local.repository.CategoryLocalRepository
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class CategoryService(
    private val categoryNetworkRepository: CategoryRepository,
    private val categoryLocalRepository: CategoryLocalRepository
) {
    suspend fun getCategories(categoryType: CategoryType, onLoad: (Response<List<Category>>) -> Unit): Response<List<Category>> = withContext(Dispatchers.IO) {
        val localResp = categoryLocalRepository.getCategoriesByType(categoryType)
        onLoad(localResp)
        delay(1000) // TODO remove
        val networkResp = categoryNetworkRepository.getCategoriesByType(categoryType)
        onLoad(networkResp)
        if (networkResp is Response.Success) {
            networkResp.responseBody.forEach {
                categoryLocalRepository.deleteCategoriesByType(categoryType)
                networkResp.responseBody.forEach { categoryLocalRepository.addCategory(it) }
            }
        }
        return@withContext networkResp
    }
}