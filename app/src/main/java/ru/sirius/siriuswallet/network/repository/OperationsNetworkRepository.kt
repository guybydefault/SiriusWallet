package ru.sirius.siriuswallet.network.repository

import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.model.Operation
import java.time.LocalDateTime
import java.time.ZoneId

class OperationsNetworkRepository {
    val retrofit = Retrofit

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