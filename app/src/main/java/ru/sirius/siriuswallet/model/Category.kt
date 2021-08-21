package ru.sirius.siriuswallet.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val userId: Int,
    val name: String,
    val type: CategoryType
)