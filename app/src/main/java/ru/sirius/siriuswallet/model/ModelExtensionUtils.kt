package ru.sirius.siriuswallet.model

import java.math.BigDecimal

fun List<Operation>.income(): BigDecimal {
    return this.sumByCategory(CategoryType.INCOME)

}

fun List<Operation>.outcome(): BigDecimal {
    return this.sumByCategory(CategoryType.OUTCOME)
}

fun List<Operation>.sumByCategory(categoryType: CategoryType): BigDecimal {
    return this.filter { it.operationCategory.type == categoryType }
        .sumOf { op -> op.amount }
}