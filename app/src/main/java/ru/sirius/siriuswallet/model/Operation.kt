package ru.sirius.siriuswallet.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Operation
    (
    val operationName: String,
    val operationCategory: String,
    val categoryResourceId: Int,
    val operationDate: LocalDateTime,
    val amount: BigDecimal
)