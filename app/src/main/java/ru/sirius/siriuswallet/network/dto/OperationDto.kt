package ru.sirius.siriuswallet.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.network.serialization.InstantSerializer
import java.math.BigDecimal
import java.time.Instant

@Serializable
data class OperationDto(
    val id: Int,
    val accountId: Int,
    val amount: Double,
    @Serializable(with = InstantSerializer::class)
    val creationDate: Instant,
    @SerialName("categoryDTO")
    val category: Category
)