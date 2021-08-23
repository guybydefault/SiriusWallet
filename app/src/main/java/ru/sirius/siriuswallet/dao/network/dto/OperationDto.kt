package ru.sirius.siriuswallet.dao.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sirius.siriuswallet.dao.network.serialization.InstantSerializer
import java.time.Instant

@Serializable
data class OperationDto(
    val id: Int,
    val accountId: Int,
    val amount: Double,
    @Serializable(with = InstantSerializer::class)
    val creationDate: Instant,
    @SerialName("categoryDTO")
    val categoryDto: CategoryDto
)