package ru.sirius.siriuswallet.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sirius.siriuswallet.network.dto.OperationDto

interface OperationsApi {

    @GET("operations/")
    suspend fun getOperations(@Query("accountId") accountId: Int): Response<List<OperationDto>>

}