package ru.sirius.siriuswallet.dao.network.dto.mappers

import ru.sirius.siriuswallet.dao.network.dto.CategoryDto
import ru.sirius.siriuswallet.model.Category

class CategoryDtoMapper(
    private val categoryResourceIdResolver: CategoryResourceIdResolver
) {
    fun mapToObj(categoryDto: CategoryDto): Category {
        return Category(
            categoryDto.id,
            categoryDto.userId,
            categoryDto.name,
            categoryDto.type,
            categoryResourceIdResolver.resolveDrawableIdByCategoryName(categoryDto.name)
        )
    }
}