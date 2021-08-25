package ru.sirius.siriuswallet.data.local.dao

import androidx.room.*
import ru.sirius.siriuswallet.data.local.entities.*

@Dao
abstract class CategoryWithOperationsDao {
    @Query("SELECT * FROM operation o JOIN category c ON c.id = o.operationCategoryId WHERE c.userId = :userId")
//    @Transaction
    abstract fun getOperationsByUserId(userId: Int): List<OperationWithCategory>

//    @Query("SELECT * FROM operation o JOIN category c ON c.id = o.operationCategoryId WHERE c.userId = :userId")
    @Query("SELECT * FROM category WHERE userId = :userId")
//    @Transaction
    abstract fun getCategoriesWithOperationsByUserId(userId: Int): List<CategoryWithOperations>

    @Query("SELECT * FROM category c WHERE c.categoryType = :type")
    abstract fun getCategoriesByType(type: DatabaseCategoryType): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategory(categoryEntity: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOperation(operationEntity: OperationEntity): Long

    @Query("DELETE FROM category")
    abstract fun deleteCategories(): Int

    @Query("DELETE FROM category WHERE categoryType = :type")
    abstract fun deleteCategoriesByType(type: DatabaseCategoryType)

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