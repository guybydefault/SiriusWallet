package ru.sirius.siriuswallet.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object DisplayFormatter {
    val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
    private val symbols: DecimalFormatSymbols = formatter.getDecimalFormatSymbols()

    init {
        symbols.setGroupingSeparator(' ')
        formatter.setDecimalFormatSymbols(symbols)
        formatter.isParseBigDecimal = true
    }
}

fun BigDecimal.formatForDisplay(): String {
    return DisplayFormatter.formatter.format(this)
}