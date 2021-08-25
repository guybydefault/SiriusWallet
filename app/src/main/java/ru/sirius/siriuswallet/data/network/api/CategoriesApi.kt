package ru.sirius.siriuswallet.data.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.sirius.siriuswallet.data.network.dto.CategoryDto

interface CategoriesApi {

    @GET("categories/")
    suspend fun getCategories(@Query("typeDTO") typeDTO: String): Response<List<CategoryDto>>

    @POST("categories/")
    suspend fun postCategory(@Body categoryDto: CategoryDto): Response<CategoryDto>

}