package com.implizstudio.android.app.androidwarungkuowner.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Token(

    @SerializedName("token")
    val token: String?

) : Parcelable