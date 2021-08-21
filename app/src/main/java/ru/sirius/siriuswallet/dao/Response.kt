package ru.sirius.siriuswallet.dao

sealed class Response<out T> {
    data class Success<T>(val responseBody: T) : Response<T>() {

    }

    data class Error(val errorMessage: String) : Response<Nothing>() {

    }
}