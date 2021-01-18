package com.implizstudio.android.app.androidwarungkuowner.data.model.response

import com.google.gson.annotations.SerializedName

data class WarungKuResponse<T>(

    @SerializedName("data")
    val data: T? = null

)