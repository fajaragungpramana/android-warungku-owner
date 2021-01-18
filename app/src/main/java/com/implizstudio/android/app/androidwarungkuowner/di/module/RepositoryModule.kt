package com.implizstudio.android.app.androidwarungkuowner.di.module

import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiDao
import com.implizstudio.android.app.androidwarungkuowner.data.repository.loading.LoadingRepository
import com.implizstudio.android.app.androidwarungkuowner.data.repository.loading.LoadingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    fun provideLoadingRepository(warungKuDao: ApiDao.WarungKu): LoadingRepository =
        LoadingRepositoryImpl(warungKuDao)

}