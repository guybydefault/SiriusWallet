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

    suspend fun getIncomeCategories(): List<Category>? {
        return retrofit.CATEGORIES_API.getCategories("INCOME").body()
    }

    suspend fun getOutcomeCategories(): List<Category>? {
        return retrofit.CATEGORIES_API.getCategories("OUTCOME").body()
    }
}