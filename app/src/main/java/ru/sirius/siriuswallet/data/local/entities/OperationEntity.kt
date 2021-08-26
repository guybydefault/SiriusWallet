package ru.sirius.siriuswallet.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.RESTRICT
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(
    tableName = "operation",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["operationCategoryId"],
            onDelete = CASCADE,
            onUpdate = RESTRICT
        )
    ],
    indices = [Index("operationCategoryId", unique = false)]
)
data class OperationEntity
    (
    @PrimaryKey val id: Int,
    val operationDate: LocalDateTime,
    val amount: BigDecimal,
    val operationCategoryId: Int
)