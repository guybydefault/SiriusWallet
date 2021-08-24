package ru.sirius.siriuswallet.data.local.converters

import androidx.room.TypeConverter
import ru.sirius.siriuswallet.data.local.entities.DatabaseCategoryType
import ru.sirius.siriuswallet.model.CategoryType

class CategoryTypeConverter {
    @TypeConverter
    fun toCategoryType(value: String) = enumValueOf<CategoryType>(value)

    @TypeConverter
    fun fromCategoryType(value: DatabaseCategoryType) = value.name
}