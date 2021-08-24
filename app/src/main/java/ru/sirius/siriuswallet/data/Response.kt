package ru.sirius.siriuswallet.data

sealed class Response<out T> {
    data class Success<T>(val responseBody: T) : Response<T>() {

    }

    data class Error(val errorMessage: String) : Response<Nothing>() {

    }
}