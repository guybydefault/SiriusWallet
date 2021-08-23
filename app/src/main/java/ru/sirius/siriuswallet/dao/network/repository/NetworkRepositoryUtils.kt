package ru.sirius.siriuswallet.dao.network.repository

import ru.sirius.siriuswallet.dao.Response

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