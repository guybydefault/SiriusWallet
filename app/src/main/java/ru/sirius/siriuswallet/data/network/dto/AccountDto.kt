package ru.sirius.siriuswallet.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("currency")
    val currency: String,
    @SerialName("balance")
    val balance: Double,
    @SerialName("income")
    val income: Double,
    @SerialName("outcome")
    val outcome: Double
)