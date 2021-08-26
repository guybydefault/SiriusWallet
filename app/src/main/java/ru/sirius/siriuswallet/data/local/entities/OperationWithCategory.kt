package ru.sirius.siriuswallet.data.local.entities

import androidx.room.Embedded

data class OperationWithCategory(
    @Embedded(prefix = "o_")
    val operationEntity: OperationEntity,
    @Embedded(prefix = "c_")
    val categoryEntity: CategoryEntity
)