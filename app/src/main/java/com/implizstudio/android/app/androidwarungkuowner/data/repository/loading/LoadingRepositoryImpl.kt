package com.implizstudio.android.app.androidwarungkuowner.data.repository.loading

import com.implizstudio.android.app.androidwarungkuowner.data.model.Version
import com.implizstudio.android.app.androidwarungkuowner.data.model.response.WarungKuResponse
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiDao
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LoadingRepositoryImpl(private val warungKuDao: ApiDao.WarungKu) : LoadingRepository {

    override suspend fun getOwnerApplicationVersion(): ApiResult<WarungKuResponse<Version>> =
        try {

            val response = GlobalScope.async { warungKuDao.getOwnerApplicationVersion() }
            val result = response.await()

            if (result.isSuccessful)
                ApiResult.Success(result.body())
            else
                ApiResult.Failure(result.code())

        } catch (exc: Throwable) {
            ApiResult.Error(exc)
        }

}