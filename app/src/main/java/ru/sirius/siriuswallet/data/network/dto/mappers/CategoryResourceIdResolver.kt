package ru.sirius.siriuswallet.data.network.dto.mappers

class CategoryResourceIdResolver {
    fun resolveDrawableIdByCategoryName(name: String): Int {
        return DefaultCategories.values().find { it.localizedName.equals(name) }?.resourceId
            ?: DefaultCategories.OTHER.resourceId
    }
}