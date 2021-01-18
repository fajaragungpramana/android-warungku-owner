package com.implizstudio.android.app.androidwarungkuowner.data.remote

import com.implizstudio.android.app.androidwarungkuowner.data.model.Auth
import com.implizstudio.android.app.androidwarungkuowner.data.model.Dashboard
import com.implizstudio.android.app.androidwarungkuowner.data.model.Token
import com.implizstudio.android.app.androidwarungkuowner.data.model.Version
import com.implizstudio.android.app.androidwarungkuowner.data.model.response.WarungKuResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiDao {

    interface WarungKu {

        @GET("android/version")
        suspend fun getOwnerApplicationVersion(): Response<WarungKuResponse<Version>>

        @POST("owner/registration")
        suspend fun doOwnerAccountRegistration(@Body data: Map<String, String?>): Response<WarungKuResponse<Auth>>

        @POST("owner/authentication")
        suspend fun doOwnerAccountAuthentication(@Body data: Map<String, String?>): Response<WarungKuResponse<Auth>>

        @GET("email/code")
        suspend fun getEmailCodeVerification(@QueryMap data: Map<String, String?>): Response<WarungKuResponse<Token>>

        @POST("verification/email")
        suspend fun doEmailVerification(
            @Body data: Map<String, String?>,
            @Header("Authorization") token: String?
        ): Response<WarungKuResponse<String>>

        @GET("owner/dashboard")
        suspend fun getOwnerDashboard(@Query("owner_id") id: String?): Response<WarungKuResponse<Dashboard>>

    }

}