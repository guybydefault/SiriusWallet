package ru.sirius.siriuswallet.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sirius.siriuswallet.data.network.serialization.InstantSerializer
import java.time.Instant

@Serializable
data class OperationDto(
    @SerialName("id")
    val id: Int,
    @SerialName("accountId")
    val accountId: Int,
    @SerialName("amount")
    val amount: Double,
    @Serializable(with = InstantSerializer::class)
    @SerialName("creationDate")
    val creationDate: Instant,
    @SerialName("categoryDTO")
    val categoryDto: CategoryDto
)