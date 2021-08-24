package ru.sirius.siriuswallet.data.network.dto.mappers

import ru.sirius.siriuswallet.R

enum class DefaultCategories(val localizedName: String, val resourceId: Int) {
    SALARY("Зарплата", R.drawable.ic_salary),
    TEMP_JOB("Подработка", R.drawable.ic_salary),
    GIFT("Подарок", R.drawable.ic_gift),
    CAPITALIZATION("Капитализация", R.drawable.ic_capitalization),
    SHOP("Супермаркеты", R.drawable.ic_supermarket),
    TRANSFER("Переводы", R.drawable.ic_transfer),
    TRANSPORT("Транспорт", R.drawable.ic_transport),
    OTHER("Другое", R.drawable.ic_salary)
}