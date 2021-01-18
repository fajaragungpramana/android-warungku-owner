package com.implizstudio.android.app.androidwarungkuowner.di.feature

import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiService
import com.implizstudio.android.app.androidwarungkuowner.data.repository.auth.AuthRepositoryImpl
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface AuthDependency {

    fun provideAuthRepository() = AuthRepositoryImpl(ApiService().warungKu())

}