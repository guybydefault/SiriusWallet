package ru.sirius.siriuswallet.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Operation
    (
    val id: Int,
    val operationDate: LocalDateTime,
    val amount: BigDecimal,
    val operationCategory: Category
) {
    val accountId = operationCategory.userId
}