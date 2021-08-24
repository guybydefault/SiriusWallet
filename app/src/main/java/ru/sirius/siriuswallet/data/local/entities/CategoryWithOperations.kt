package ru.sirius.siriuswallet.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithOperations(
    @Embedded
    val category: CategoryEntity,
    @Relation(
        entity = OperationEntity::class,
        parentColumn = "id",
        entityColumn = "operationCategoryId"
    )
    val operations: List<OperationEntity>
)