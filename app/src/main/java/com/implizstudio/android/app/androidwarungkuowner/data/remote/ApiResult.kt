package com.implizstudio.android.app.androidwarungkuowner.data.remote

sealed class ApiResult<T> {

    data class Success<T>(val body: T?, val code: Int? = null) : ApiResult<T>()
    data class Failure<T>(val code: Int?) : ApiResult<T>()
    data class Error<T>(val exc: Throwable?) : ApiResult<T>()

}
