package com.implizstudio.android.app.androidwarungkuowner.data.model

import com.google.gson.annotations.SerializedName

data class Tip(

    @SerializedName("image")
    val image: String?,

    @SerializedName("title")
    val title: String?

)