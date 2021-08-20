package ru.sirius.siriuswallet

import android.os.Build
import androidx.annotation.RequiresApi
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class DataRepository {
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

}