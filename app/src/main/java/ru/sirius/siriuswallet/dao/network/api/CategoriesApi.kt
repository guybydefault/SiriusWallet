package ru.sirius.siriuswallet.dao.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sirius.siriuswallet.dao.network.dto.CategoryDto

interface CategoriesApi {

    @GET("categories/")
    suspend fun getCategories(@Query("typeDTO") typeDTO: String): Response<List<CategoryDto>>

}