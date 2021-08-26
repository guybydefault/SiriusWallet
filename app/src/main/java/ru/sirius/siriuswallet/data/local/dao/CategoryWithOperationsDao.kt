package ru.sirius.siriuswallet.data.local.dao

import androidx.room.*
import ru.sirius.siriuswallet.data.local.entities.*

@Dao
abstract class CategoryWithOperationsDao {
    @Query("SELECT o.id as o_id, o.amount as o_amount, o.operationDate as o_operationDate, o.operationCategoryId as o_operationCategoryId, c.id as c_id, c.userId as c_userId, c.categoryType as c_categoryType, c.name as c_name, c.categoryResourceId as c_categoryResourceId FROM operation o JOIN category AS c ON c.id = o.operationCategoryId WHERE c.userId = :userId")
    abstract fun getOperationsByUserId(userId: Int): List<OperationWithCategory>

    @Query("SELECT * FROM category WHERE userId = :userId")
    @Transaction
    abstract fun getCategoriesWithOperationsByUserId(userId: Int): List<CategoryWithOperations>

    @Query("SELECT * FROM category c WHERE c.categoryType = :type")
    abstract fun getCategoriesByType(type: DatabaseCategoryType): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertCategoryIfNotExists(categoryEntity: CategoryEntity): Long

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
        insertCategoryIfNotExists(categoryEntity)
        return insertOperation(operationEntity)
    }

    @Transaction
    open fun deleteAll() {
        deleteOperations()
        deleteCategories()
    }
}