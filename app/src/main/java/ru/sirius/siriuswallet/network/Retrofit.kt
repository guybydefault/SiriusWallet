package ru.guybydefault.minin.api

import com.example.rxplusnetworklab.ApiTokenInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object Retrofit {
    const val BASE_URL = "http://localhost:8080/api/"

    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiTokenInterceptor("test"))
        .build()

    private val retrofit: Retrofit
    val CATEGORIES_API: CategoriesApi

    init {
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(
                @OptIn(ExperimentalSerializationApi::class)
                json.asConverterFactory(contentType)
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // TODO createAsync?
            .build()

        CATEGORIES_API = retrofit.create(CategoriesApi::class.java)
    }
}
