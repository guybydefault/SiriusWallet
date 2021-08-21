package ru.sirius.siriuswallet.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sirius.siriuswallet.model.Category

interface CategoriesApi {

    @GET("categories/")
    suspend fun getCategories(@Query("typeDTO") typeDTO: String): Response<List<Category>>

}