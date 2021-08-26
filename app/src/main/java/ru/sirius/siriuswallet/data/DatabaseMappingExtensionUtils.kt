package ru.sirius.siriuswallet.data

import ru.sirius.siriuswallet.data.local.entities.CategoryEntity
import ru.sirius.siriuswallet.data.local.entities.DatabaseCategoryType
import ru.sirius.siriuswallet.data.local.entities.OperationEntity
import ru.sirius.siriuswallet.data.local.entities.OperationWithCategory
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType
import ru.sirius.siriuswallet.model.Operation

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(this.id, this.userId, this.name, this.type.toCategoryDBType(), this.categoryResourceId)
}

fun CategoryType.toCategoryDBType(): DatabaseCategoryType {
    return when (this) {
        CategoryType.INCOME -> DatabaseCategoryType.INCOME
        CategoryType.OUTCOME -> DatabaseCategoryType.OUTCOME
    }
}

fun DatabaseCategoryType.toCategoryType(): CategoryType {
    return when (this) {
        DatabaseCategoryType.INCOME -> CategoryType.INCOME
        DatabaseCategoryType.OUTCOME -> CategoryType.OUTCOME
    }
}

fun Operation.toOperationEntity(): OperationEntity {
    return OperationEntity(this.id, this.operationDate, this.amount, this.operationCategory.id)
}

fun OperationEntity.toOperation(categoryEntity: CategoryEntity): Operation {
    return Operation(this.id, this.operationDate, this.amount, categoryEntity.toCategory())
}

fun CategoryEntity.toCategory(): Category {
    return Category(this.id, this.userId, this.name, this.categoryType.toCategoryType(), this.categoryResourceId)
}

fun OperationWithCategory.toOperation(): Operation {
    return this.operationEntity.toOperation(this.categoryEntity)
}

fun OperationWithCategory.toCategory(): Category {
    return this.categoryEntity.toCategory()
}