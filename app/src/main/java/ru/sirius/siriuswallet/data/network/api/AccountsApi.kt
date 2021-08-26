package ru.sirius.siriuswallet.data.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sirius.siriuswallet.data.network.dto.AccountDto

interface AccountsApi {
    @GET("accounts/")
    suspend fun getAccounts(): Response<List<AccountDto>>

    @GET("accounts/{id}")
    suspend fun getAccountInfo(@Path("id") id: Int): Response<AccountDto>
}