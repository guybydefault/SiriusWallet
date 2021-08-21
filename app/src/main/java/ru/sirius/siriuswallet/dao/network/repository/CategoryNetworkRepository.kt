package ru.sirius.siriuswallet.dao.network.repository

import android.os.Build
import androidx.annotation.RequiresApi
import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class CategoryNetworkRepository {
    val retrofit = Retrofit

    val dataset: List<Operation> = mutableListOf(
        Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now(), BigDecimal(-12000),
        ),
        Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now(), BigDecimal(130000),
        ), Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(1), BigDecimal(130000),
        ), Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now().minusDays(2), BigDecimal(-12000),
        ), Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(2), BigDecimal(130000)
        ),
        Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(4), BigDecimal(130000)
        ),
        Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now().minusDays(4), BigDecimal(-12000)
        )
    )

    suspend fun getIncomeCategories(): List<Category>? {
        return retrofit.CATEGORIES_API.getCategories("INCOME").body()
    }

    suspend fun getOutcomeCategories(): List<Category>? {
        return retrofit.CATEGORIES_API.getCategories("OUTCOME").body()
    }
}