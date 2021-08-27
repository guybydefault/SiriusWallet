package ru.sirius.siriuswallet.data.network.repository

import android.os.Build
import androidx.annotation.RequiresApi
import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.data.CategoryRepository
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.data.network.dto.mappers.CategoryDtoMapper
import ru.sirius.siriuswallet.data.network.dto.mappers.CategoryResourceIdResolver
import ru.sirius.siriuswallet.data.toCategoryDto
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

@RequiresApi(Build.VERSION_CODES.O)
class CategoryNetworkRepository : CategoryRepository {
    val retrofit = Retrofit
    val categoryDtoMapper = CategoryDtoMapper(CategoryResourceIdResolver())

    override suspend fun getCategoriesByTypeAndUserId(categoryType: CategoryType, accountId: Int): Response<List<Category>> {
        return retrofitRequestExceptionHandler {
            retrofit.CATEGORIES_API.getCategories(categoryType.name, accountId)
                .handleResponse { it.map { categoryDtoMapper.mapToObj(it) } }
        }
    }

    override suspend fun addCategory(category: Category): Response<Category> {
        return retrofitRequestExceptionHandler {
            retrofit.CATEGORIES_API.postCategory(category.toCategoryDto()).handleResponse { categoryDtoMapper.mapToObj(it) }
        }
    }
}