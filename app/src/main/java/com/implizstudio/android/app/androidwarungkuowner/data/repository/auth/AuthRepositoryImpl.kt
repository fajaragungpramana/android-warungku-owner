package com.implizstudio.android.app.androidwarungkuowner.data.repository.auth

import com.implizstudio.android.app.androidwarungkuowner.data.model.Auth
import com.implizstudio.android.app.androidwarungkuowner.data.model.Token
import com.implizstudio.android.app.androidwarungkuowner.data.model.response.WarungKuResponse
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiDao
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val warungKuDao: ApiDao.WarungKu) :
    AuthRepository {

    override suspend fun doOwnerAccountRegistration(data: Map<String, String?>): ApiResult<WarungKuResponse<Auth>> =
        try {

            val response = GlobalScope.async { warungKuDao.doOwnerAccountRegistration(data) }
            val result = response.await()

            if (result.isSuccessful)
                ApiResult.Success(result.body(), result.code())
            else
                ApiResult.Failure(result.code())

        } catch (exc: Throwable) {
            ApiResult.Error(exc)
        }

    override suspend fun doOwnerAccountAuthentication(data: Map<String, String?>): ApiResult<WarungKuResponse<Auth>> =
        try {

            val response = GlobalScope.async { warungKuDao.doOwnerAccountAuthentication(data) }
            val result = response.await()

            if (result.isSuccessful)
                ApiResult.Success(result.body(), result.code())
            else
                ApiResult.Failure(result.code())

        } catch (exc: Throwable) {
            ApiResult.Error(exc)
        }

    override suspend fun getEmailCodeVerification(data: Map<String, String?>): ApiResult<WarungKuResponse<Token>> =
        try {

            val response = GlobalScope.async { warungKuDao.getEmailCodeVerification(data) }
            val result = response.await()

            if (result.isSuccessful)
                ApiResult.Success(result.body(), result.code())
            else
                ApiResult.Failure(result.code())

        } catch (exc: Throwable) {
            ApiResult.Error(exc)
        }

    override suspend fun doEmailVerification(data: Map<String, String?>, token: String?): ApiResult<WarungKuResponse<String>> =
        try {

            val response = GlobalScope.async { warungKuDao.doEmailVerification(data, token) }
            val result = response.await()

            if (result.isSuccessful)
                ApiResult.Success(result.body(), result.code())
            else
                ApiResult.Failure(result.code())

        } catch (exc: Throwable) {
            ApiResult.Error(exc)
        }

}