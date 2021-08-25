package ru.sirius.siriuswallet.data.local.dao

import androidx.room.*
import ru.sirius.siriuswallet.data.local.entities.CategoryEntity
import ru.sirius.siriuswallet.data.local.entities.DatabaseCategoryType
import ru.sirius.siriuswallet.data.local.entities.OperationEntity
import ru.sirius.siriuswallet.data.local.entities.OperationWithCategory

@Dao
abstract class CategoryWithOperationsDao {
    @Query("SELECT * FROM operation o JOIN category c ON c.id = o.operationCategoryId WHERE userId = :userId")
    @Transaction
    abstract fun getOperationsByUserId(userId: Int): List<OperationWithCategory>

    @Query("SELECT * FROM category c WHERE c.categoryType = :type")
    abstract fun getCategoriesByType(type: DatabaseCategoryType): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOperation(operationEntity: OperationEntity): Long

    @Query("DELETE FROM category")
    abstract fun deleteCategories(): Int

    @Query("DELETE FROM operation")
    abstract fun deleteOperations(): Int

    @Transaction
    open fun insertOperationWithCategory(operationEntity: OperationEntity, categoryEntity: CategoryEntity): Long {
        insertCategory(categoryEntity)
        return insertOperation(operationEntity)
    }

    @Transaction
    open fun deleteAll() {
        deleteOperations()
        deleteCategories()
    }
}