package ru.sirius.siriuswallet.model

import kotlinx.serialization.Serializable
import ru.sirius.siriuswallet.model.CategoryType

data class Category(
    val id: Int,
    val userId: Int,
    val name: String,
    val type: CategoryType,
    val categoryResourceId: Int
)