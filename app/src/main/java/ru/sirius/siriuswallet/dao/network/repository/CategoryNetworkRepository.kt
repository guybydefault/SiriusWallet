package ru.sirius.siriuswallet.dao.network.repository

import android.os.Build
import androidx.annotation.RequiresApi
import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.dao.Response
import ru.sirius.siriuswallet.dao.network.dto.mappers.CategoryDtoMapper
import ru.sirius.siriuswallet.dao.network.dto.mappers.CategoryResourceIdResolver
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

@RequiresApi(Build.VERSION_CODES.O)
class CategoryNetworkRepository {
    val retrofit = Retrofit
    val categoryDtoMapper = CategoryDtoMapper(CategoryResourceIdResolver())

    suspend fun getCategories(categoryType: CategoryType): Response<List<Category>> {
        return retrofit.CATEGORIES_API.getCategories(categoryType.name)
            .handleResponse { it.map { categoryDtoMapper.mapToObj(it) } }
    }
}