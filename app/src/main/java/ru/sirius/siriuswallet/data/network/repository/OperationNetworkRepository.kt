package ru.sirius.siriuswallet.data.network.repository

import ru.guybydefault.minin.api.Retrofit
import ru.sirius.siriuswallet.data.OperationRepository
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.data.network.dto.mappers.CategoryDtoMapper
import ru.sirius.siriuswallet.data.network.dto.mappers.CategoryResourceIdResolver
import ru.sirius.siriuswallet.data.toCreateOperationDto
import ru.sirius.siriuswallet.model.Operation
import java.time.LocalDateTime
import java.time.ZoneId

class OperationNetworkRepository : OperationRepository {
    val retrofit = Retrofit
    val categoryDtoMapper = CategoryDtoMapper(CategoryResourceIdResolver())

    override suspend fun getOperationsByAccountId(accountId: Int): Response<List<Operation>> {
        return retrofitRequestExceptionHandler {
            retrofit.OPERATIONS_API.getOperations(accountId).handleResponse {
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

    override suspend fun insertOperation(operation: Operation): Response<Int> {
        return retrofitRequestExceptionHandler {
            retrofit.OPERATIONS_API.postOperation(operation.toCreateOperationDto()).handleResponse {
                it.id
            }
        }
    }
}