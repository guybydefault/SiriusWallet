package ru.sirius.siriuswallet.network

import android.os.Build
import androidx.annotation.RequiresApi
import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
class DataRepository {
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

    suspend fun getOperations(accountId: Int): List<Operation> {
        return retrofit.OPERATIONS_API.getOperations(accountId).body()!!.map {
            Operation(
                it.category.name,
                it.category.type.typeLocalizedName,
                R.drawable.ic_salary,
                LocalDateTime.ofInstant(it.creationDate, ZoneId.of("UTC")),
                it.amount.toBigDecimal()
            )
        }
    }

}