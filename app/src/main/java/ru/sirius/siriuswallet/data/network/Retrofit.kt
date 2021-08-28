package ru.guybydefault.minin.api

import com.example.rxplusnetworklab.ApiTokenInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.sirius.siriuswallet.ApplicationConstants
import ru.sirius.siriuswallet.data.network.api.AccountsApi
import ru.sirius.siriuswallet.data.network.api.CategoriesApi
import ru.sirius.siriuswallet.data.network.api.OperationsApi
import java.util.concurrent.TimeUnit

object Retrofit {
    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(4, TimeUnit.SECONDS)
        .connectTimeout(4, TimeUnit.SECONDS)
        .callTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(ApiTokenInterceptor(ApplicationConstants.USER_TOKEN))
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit: Retrofit
    val CATEGORIES_API: CategoriesApi
    val OPERATIONS_API: OperationsApi
    val ACCOUNTS_API: AccountsApi

    init {
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApplicationConstants.BASE_URL)
            .addConverterFactory(
                @OptIn(ExperimentalSerializationApi::class)
                json.asConverterFactory(contentType)
            )
            .build()

        CATEGORIES_API = retrofit.create(CategoriesApi::class.java)
        OPERATIONS_API = retrofit.create(OperationsApi::class.java)
        ACCOUNTS_API = retrofit.create(AccountsApi::class.java)
    }
}
