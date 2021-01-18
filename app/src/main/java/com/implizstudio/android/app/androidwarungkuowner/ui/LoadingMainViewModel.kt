package com.implizstudio.android.app.androidwarungkuowner.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.implizstudio.android.app.androidwarungkuowner.data.model.Version
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult
import com.implizstudio.android.app.androidwarungkuowner.data.repository.loading.LoadingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoadingMainViewModel @ViewModelInject constructor(
    private val loadingRepository: LoadingRepository
) : ViewModel() {

    private val _ownerApplicationVersion = MutableLiveData<Version>()
    val ownerApplicationVersion: LiveData<Version>
        get() {

            GlobalScope.launch {
                when (val response = loadingRepository.getOwnerApplicationVersion()) {
                    is ApiResult.Success -> _ownerApplicationVersion.postValue(response.body?.data)
                }
            }

            return _ownerApplicationVersion
        }

}