package com.implizstudio.android.app.androidwarungkuowner.data.remote

import com.implizstudio.android.app.androidwarungkuowner.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    private companion object {
        const val COMMUNICATION = 30L
    }

    private fun httpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    private fun okHttpClientWarungKu(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(COMMUNICATION, TimeUnit.SECONDS)
            .writeTimeout(COMMUNICATION, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .addHeader("access_key", BuildConfig.ACCESS_KEY)
                        .build()
                )
            }
            .build()

    fun warungKu(): ApiDao.WarungKu =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientWarungKu())
            .build()
            .create(ApiDao.WarungKu::class.java)

}