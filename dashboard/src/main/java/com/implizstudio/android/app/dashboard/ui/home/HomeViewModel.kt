package com.implizstudio.android.app.dashboard.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.implizstudio.android.app.androidwarungkuowner.data.model.Dashboard
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult
import com.implizstudio.android.app.androidwarungkuowner.data.repository.dashboard.DashboardRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor() : ViewModel() {

    private val _dashboard = MutableLiveData<Dashboard>()
    fun getDashboard(dashboardRepository: DashboardRepository, id: String?): LiveData<Dashboard> {
        GlobalScope.launch {
            when (val response = dashboardRepository.getOwnerDashboard(id)) {

                is ApiResult.Success -> _dashboard.postValue(response.body?.data)

            }
        }
        return _dashboard
    }

}