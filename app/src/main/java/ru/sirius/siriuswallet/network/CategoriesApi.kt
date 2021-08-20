package ru.guybydefault.minin.api

import retrofit2.Response
import retrofit2.http.GET
import ru.sirius.siriuswallet.model.Category

interface CategoriesApi {

    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>

}