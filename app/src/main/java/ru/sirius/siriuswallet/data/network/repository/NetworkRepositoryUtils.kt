package ru.sirius.siriuswallet.data.network.repository

import retrofit2.HttpException
import ru.sirius.siriuswallet.data.Response
import java.net.ConnectException

fun <T> retrofit2.Response<T>.handleResponse(): Response<T> {
    return this.handleResponse { it }
}

fun <T, R> retrofit2.Response<T>.handleResponse(transformFunc: (T) -> (R)): Response<R> {
    val resp = this
    if (!resp.isSuccessful) {
        return Response.Error("Request failed with status code ${resp.code()}")
    } else if (resp.body() == null) {
        return Response.Error("Return body is null")
    } else {
        val body = transformFunc(resp.body()!!)
        return Response.Success(body)
    }
}

inline fun <T> retrofitRequestExceptionHandler(retrofitRequest: () -> Response<T>): Response<T> {
    try {
        return retrofitRequest.invoke()
    } catch (e: HttpException) {
        return Response.Error(e.message())
    } catch (e: ConnectException) {
        return Response.Error(e.localizedMessage ?: "Connect exception")
    }
}