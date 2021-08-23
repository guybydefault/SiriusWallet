package ru.sirius.siriuswallet.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sirius.siriuswallet.dao.network.repository.CategoryNetworkRepository
import ru.sirius.siriuswallet.model.CategoryType

class CategoryService {
    private val categoryNetworkRepository = CategoryNetworkRepository()

    suspend fun getCategories(categoryType: CategoryType) = withContext(Dispatchers.IO) {
        return@withContext categoryNetworkRepository.getCategories(categoryType)
    }


}