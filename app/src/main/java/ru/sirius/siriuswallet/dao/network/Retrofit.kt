package ru.guybydefault.minin.api

import com.example.rxplusnetworklab.ApiTokenInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.sirius.siriuswallet.dao.network.api.CategoriesApi
import ru.sirius.siriuswallet.dao.network.api.OperationsApi

object Retrofit {
    const val BASE_URL = "http://bbcc-85-174-236-130.ngrok.io/api/"

    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiTokenInterceptor("test"))
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit: Retrofit
    val CATEGORIES_API: CategoriesApi
    val OPERATIONS_API: OperationsApi

    init {
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(
                @OptIn(ExperimentalSerializationApi::class)
                json.asConverterFactory(contentType)
            )
            .build()

        CATEGORIES_API = retrofit.create(CategoriesApi::class.java)
        OPERATIONS_API = retrofit.create(OperationsApi::class.java)
    }
}
