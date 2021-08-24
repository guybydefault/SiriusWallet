package ru.sirius.siriuswallet.data.local.converters

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class BigDecimalConverter {
    @TypeConverter
    fun fromBigDecimal(decimal: BigDecimal): String {
        return DBBigDecimalFormatter.formatter.format(decimal)
    }

    @TypeConverter
    fun toBigDecimal(str: String): BigDecimal {
        return DBBigDecimalFormatter.formatter.parse(str) as BigDecimal
    }

    object DBBigDecimalFormatter {
        val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
        private val symbols: DecimalFormatSymbols = formatter.getDecimalFormatSymbols()

        init {
            symbols.decimalSeparator = '.'
            formatter.setDecimalFormatSymbols(symbols)
            formatter.isParseBigDecimal = true
        }
    }
}