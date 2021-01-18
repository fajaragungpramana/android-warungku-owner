package com.implizstudio.android.app.androidwarungkuowner.data.repository.loading

import com.implizstudio.android.app.androidwarungkuowner.data.model.Version
import com.implizstudio.android.app.androidwarungkuowner.data.model.response.WarungKuResponse
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult

interface LoadingRepository {

    suspend fun getOwnerApplicationVersion(): ApiResult<WarungKuResponse<Version>>

}