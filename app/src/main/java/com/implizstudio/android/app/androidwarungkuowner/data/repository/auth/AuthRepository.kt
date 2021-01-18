package com.implizstudio.android.app.androidwarungkuowner.data.repository.auth

import com.implizstudio.android.app.androidwarungkuowner.data.model.Auth
import com.implizstudio.android.app.androidwarungkuowner.data.model.Token
import com.implizstudio.android.app.androidwarungkuowner.data.model.response.WarungKuResponse
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult

interface AuthRepository {

    suspend fun doOwnerAccountRegistration(data: Map<String, String?>): ApiResult<WarungKuResponse<Auth>>

    suspend fun doOwnerAccountAuthentication(data: Map<String, String?>): ApiResult<WarungKuResponse<Auth>>

    suspend fun getEmailCodeVerification(data: Map<String, String?>): ApiResult<WarungKuResponse<Token>>

    suspend fun doEmailVerification(data: Map<String, String?>, token: String?): ApiResult<WarungKuResponse<String>>

}