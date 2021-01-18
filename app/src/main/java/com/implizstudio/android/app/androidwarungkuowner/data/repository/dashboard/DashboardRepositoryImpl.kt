package com.implizstudio.android.app.androidwarungkuowner.data.repository.dashboard

import com.implizstudio.android.app.androidwarungkuowner.data.model.Dashboard
import com.implizstudio.android.app.androidwarungkuowner.data.model.response.WarungKuResponse
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiDao
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(private val warungKuDao: ApiDao.WarungKu) :
    DashboardRepository {

    override suspend fun getOwnerDashboard(id: String?): ApiResult<WarungKuResponse<Dashboard>> =
        try {

            val response = GlobalScope.async { warungKuDao.getOwnerDashboard(id) }
            val result = response.await()

            if (result.isSuccessful)
                ApiResult.Success(result.body(), result.code())
            else
                ApiResult.Failure(result.code())

        } catch (exc: Throwable) {
            ApiResult.Error(exc)
        }

}