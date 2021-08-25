package ru.sirius.siriuswallet.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class OperationWithCategory(
    @Embedded
    val operation: OperationEntity,
    @Relation(
        entity = CategoryEntity::class,
        parentColumn = "operationCategoryId",
        entityColumn = "id"
    ) val categoryEntity: CategoryEntity
)