package ru.sirius.siriuswallet.data.local.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateConverter {
    @TypeConverter
    fun convertLocalDateToString(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }

    @TypeConverter
    fun convertStringToLocalDateTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }
}