package com.implizstudio.android.app.auth.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.implizstudio.android.app.androidwarungkuowner.data.model.Auth
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult
import com.implizstudio.android.app.androidwarungkuowner.data.repository.auth.AuthRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor() : ViewModel() {

    private val _progressEnable = MutableLiveData<Boolean>()
    val progressEnable: LiveData<Boolean>
        get() = _progressEnable

    private val _auth = MutableLiveData<Auth>()
    val auth: LiveData<Auth>
        get() = _auth

    private val _httpResponseCode = MutableLiveData<Int>()
    val httpResponseCode: LiveData<Int>
        get() = _httpResponseCode

    fun doOwnerAccountAuthentication(authRepository: AuthRepository, data: Map<String, String?>) {

        _progressEnable.value = true
        GlobalScope.launch {

            when (val response = authRepository.doOwnerAccountAuthentication(data)) {

                is ApiResult.Success -> {
                    _progressEnable.postValue(false)
                    _auth.postValue(response.body?.data)
                    _httpResponseCode.postValue(response.code)
                }

                is ApiResult.Failure -> {
                    _progressEnable.postValue(false)
                    _httpResponseCode.postValue(response.code)
                }

                is ApiResult.Error -> _progressEnable.postValue(false)

            }

        }

    }

}