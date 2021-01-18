package com.implizstudio.android.app.androidwarungkuowner.di.module

import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideWarungKu() = ApiService().warungKu()

}