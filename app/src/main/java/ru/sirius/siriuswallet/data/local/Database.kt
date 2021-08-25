package ru.sirius.siriuswallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.sirius.siriuswallet.data.local.converters.BigDecimalConverter
import ru.sirius.siriuswallet.data.local.converters.CategoryTypeConverter
import ru.sirius.siriuswallet.data.local.converters.LocalDateConverter
import ru.sirius.siriuswallet.data.local.dao.CategoryWithOperationsDao
import ru.sirius.siriuswallet.data.local.entities.CategoryEntity
import ru.sirius.siriuswallet.data.local.entities.OperationEntity

@Database(
    entities = [CategoryEntity::class, OperationEntity::class],
    version = 1
)
@TypeConverters(CategoryTypeConverter::class, LocalDateConverter::class, BigDecimalConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun categoryWithOperationsDao(): CategoryWithOperationsDao
}