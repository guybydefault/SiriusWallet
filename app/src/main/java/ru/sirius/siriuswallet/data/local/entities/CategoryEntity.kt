package ru.sirius.siriuswallet.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "category",
    indices = [Index("userId", "name", unique = true)]
)
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val name: String,
    val categoryType: DatabaseCategoryType,
    val categoryResourceId: Int
)