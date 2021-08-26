package ru.sirius.siriuswallet.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sirius.siriuswallet.model.CategoryType

@Serializable
data class CategoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: CategoryType
)