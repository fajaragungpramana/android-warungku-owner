package com.implizstudio.android.app.androidwarungkuowner.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Auth(

    @SerializedName("id")
    val id: String?,

    @SerializedName("full_name")
    val fullName: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("auth")
    val auth: Token

) : Parcelable