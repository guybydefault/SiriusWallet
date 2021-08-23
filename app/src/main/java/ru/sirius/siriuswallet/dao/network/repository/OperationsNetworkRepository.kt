package ru.sirius.siriuswallet.dao.network.repository

import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.dao.Response
import ru.sirius.siriuswallet.dao.network.dto.mappers.CategoryDtoMapper
import ru.sirius.siriuswallet.dao.network.dto.mappers.CategoryResourceIdResolver
import ru.sirius.siriuswallet.model.Operation
import java.time.LocalDateTime
import java.time.ZoneId

class OperationsNetworkRepository {
    val retrofit = Retrofit
    val categoryDtoMapper = CategoryDtoMapper(CategoryResourceIdResolver())

    suspend fun getOperations(accountId: Int): Response<List<Operation>> {
        return retrofit.OPERATIONS_API.getOperations(accountId).handleResponse {
            it.map {
                Operation(
                    it.id,
                    LocalDateTime.ofInstant(it.creationDate, ZoneId.of("UTC")),
                    it.amount.toBigDecimal(),
                    categoryDtoMapper.mapToObj(it.categoryDto)
                )
            }
        }
    }
}