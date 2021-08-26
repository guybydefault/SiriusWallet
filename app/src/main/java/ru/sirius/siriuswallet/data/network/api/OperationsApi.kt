package ru.sirius.siriuswallet.data.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.sirius.siriuswallet.data.network.dto.OperationCreateRequestDto
import ru.sirius.siriuswallet.data.network.dto.OperationDto

interface OperationsApi {

    @GET("operations/")
    suspend fun getOperations(@Query("accountId") accountId: Int): Response<List<OperationDto>>

    @POST("operations/")
    suspend fun postOperation(@Body operationDto: OperationCreateRequestDto): Response<OperationDto>

}