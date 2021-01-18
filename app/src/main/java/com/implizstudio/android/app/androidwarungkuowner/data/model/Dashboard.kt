package com.implizstudio.android.app.androidwarungkuowner.data.model

import com.google.gson.annotations.SerializedName

data class Dashboard(

    @SerializedName("income_today")
    val incomeToday: Long?,

    @SerializedName("profit_today")
    val profitToday: Long?,

    @SerializedName("percentage")
    val percentage: String?,

    @SerializedName("tips")
    val tips: List<Tip>

)