package ru.sirius.siriuswallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class CategoryWithOperationsDao {
    @Query("SELECT * FROM category")
    @Transaction
    abstract fun getAll(): List<CategoryWithOperationsDao>
}