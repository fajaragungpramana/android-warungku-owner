package com.implizstudio.android.app.dashboard.di

import android.content.Context
import com.implizstudio.android.app.androidwarungkuowner.di.feature.DashboardDependency
import com.implizstudio.android.app.dashboard.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [DashboardDependency::class])
interface DashboardComponent {

    fun inject(homeFragment: HomeFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependency(dashboardDependency: DashboardDependency): Builder
        fun build(): DashboardComponent
    }

}