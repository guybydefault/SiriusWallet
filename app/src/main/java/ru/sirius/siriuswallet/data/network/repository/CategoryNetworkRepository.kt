package ru.sirius.siriuswallet.data.network.repository

import android.os.Build
import androidx.annotation.RequiresApi
import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.data.CategoryRepository
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.data.network.dto.mappers.CategoryDtoMapper
import ru.sirius.siriuswallet.data.network.dto.mappers.CategoryResourceIdResolver
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

@RequiresApi(Build.VERSION_CODES.O)
class CategoryNetworkRepository : CategoryRepository {
    val retrofit = Retrofit
    val categoryDtoMapper = CategoryDtoMapper(CategoryResourceIdResolver())

    override suspend fun getCategoriesByType(categoryType: CategoryType): Response<List<Category>> {
        return retrofitRequestExceptionHandler {
            retrofit.CATEGORIES_API.getCategories(categoryType.name)
                .handleResponse { it.map { categoryDtoMapper.mapToObj(it) } }
        }
    }
}