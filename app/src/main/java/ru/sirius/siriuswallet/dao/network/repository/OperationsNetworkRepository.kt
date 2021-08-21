package ru.sirius.siriuswallet.dao.network.repository

import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.dao.Response
import ru.sirius.siriuswallet.model.Operation
import java.time.LocalDateTime
import java.time.ZoneId

class OperationsNetworkRepository {
    val retrofit = Retrofit

    suspend fun getOperations(accountId: Int): Response<List<Operation>> {
        val resp = retrofit.OPERATIONS_API.getOperations(accountId)
        if (!resp.isSuccessful) {
            return Response.Error("Request failed with status code ${resp.code()}")
        } else if (resp.body() == null) {
            return Response.Error("Return body is null")
        } else {
            val operations = retrofit.OPERATIONS_API.getOperations(accountId).body()!!.map {
                Operation(
                    it.category.name,
                    it.category.type.typeLocalizedName,
                    R.drawable.ic_salary,
                    LocalDateTime.ofInstant(it.creationDate, ZoneId.of("UTC")),
                    it.amount.toBigDecimal()
                )
            }
            return Response.Success(operations)
        }
    }
}