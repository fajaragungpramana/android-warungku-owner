package com.implizstudio.android.app.auth.ui.verification

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.implizstudio.android.app.androidwarungkuowner.data.model.Token
import com.implizstudio.android.app.androidwarungkuowner.data.remote.ApiResult
import com.implizstudio.android.app.androidwarungkuowner.data.repository.auth.AuthRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VerificationViewModel @ViewModelInject constructor() : ViewModel() {

    private val _progressEnable = MutableLiveData<Boolean>()
    val progressEnable: LiveData<Boolean>
        get() = _progressEnable

    private val _accessToken = MutableLiveData<Token>()
    val accessToken: LiveData<Token>
        get() = _accessToken

    private val _httpResponseCode = MutableLiveData<Int>()
    val httpResponseCode: LiveData<Int>
        get() = _httpResponseCode

    fun getEmailCodeVerification(authRepository: AuthRepository, data: Map<String, String?>) {

        _progressEnable.value = true
        GlobalScope.launch {

            when (val response = authRepository.getEmailCodeVerification(data)) {

                is ApiResult.Success -> {
                    _progressEnable.postValue(false)
                    _accessToken.postValue(response.body?.data)
                }

                is ApiResult.Failure -> _progressEnable.postValue(false)

                is ApiResult.Error -> _progressEnable.postValue(false)

            }

        }

    }

    fun doEmailVerification(authRepository: AuthRepository, data: Map<String, String?>, token: String?) {

        _progressEnable.value = true
        GlobalScope.launch {

            when (val response = authRepository.doEmailVerification(data, token)) {

                is ApiResult.Success -> {
                    _progressEnable.postValue(false)
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