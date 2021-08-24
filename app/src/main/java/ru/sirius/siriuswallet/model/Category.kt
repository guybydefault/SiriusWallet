package ru.sirius.siriuswallet.model

data class Category(
    val id: Int,
    val userId: Int,
    val name: String,
    val type: CategoryType,
    val categoryResourceId: Int
)