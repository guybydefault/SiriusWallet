package com.example.rxplusnetworklab

import okhttp3.Interceptor
import okhttp3.Response

class ApiTokenInterceptor(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newHeaders = request.headers.newBuilder().add("name", token).build()

        return chain.proceed(
            request.newBuilder()
                .headers(newHeaders)
                .build()
        )
    }
}
