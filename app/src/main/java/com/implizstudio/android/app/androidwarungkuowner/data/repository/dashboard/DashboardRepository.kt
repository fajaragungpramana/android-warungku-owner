package com.implizstudio.android.app.androidwarungkuowner.data.repository.dashboard

import com.implizstudio.android.app.androidwarungkuowner.data.model.Dashboard
import com.implizstudio.android.app.androidwarungkuowner.data.model.response.WarungKuResponse
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult

interface DashboardRepository {

    suspend fun getOwnerDashboard(id: String?): ApiResult<WarungKuResponse<Dashboard>>

}