package ru.sirius.siriuswallet.dao.network.dto

import kotlinx.serialization.Serializable
import ru.sirius.siriuswallet.model.CategoryType

@Serializable
data class CategoryDto(
    val id: Int,
    val userId: Int,
    val name: String,
    val type: CategoryType
)