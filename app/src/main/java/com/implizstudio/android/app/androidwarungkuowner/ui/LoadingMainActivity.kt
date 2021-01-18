package com.implizstudio.android.app.androidwarungkuowner.ui

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.implizstudio.android.app.androidwarungkuowner.BuildConfig
import com.implizstudio.android.app.androidwarungkuowner.data.model.constant.Constant
import com.implizstudio.android.app.androidwarungkuowner.databinding.ActivityMainLoadingBinding
import com.implizstudio.android.app.resources.base.ActivityBase
import com.implizstudio.android.app.resources.extension.getDataFromSharedPreference
import com.implizstudio.android.app.resources.extension.startActivityModule
import com.implizstudio.android.app.resources.util.NavigationModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingMainActivity : ActivityBase() {

    private companion object {
        const val LOADING_TIME = 2000L
    }

    private val viewModel by viewModels<LoadingMainViewModel>()

    override fun onCreated(savedInstanceState: Bundle?) {
        val binding = ActivityMainLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            viewModel.ownerApplicationVersion.observe(this, {
                if (it.owner == BuildConfig.VERSION_NAME)
                    if (getDataFromSharedPreference(Constant.Key.IS_INTRO, false))
                        if (getDataFromSharedPreference(Constant.Key.IS_LOG_IN, false))
                            startActivityModule(NavigationModule.GOTO_DASHBOARD)
                        else
                            startActivityModule(NavigationModule.GOTO_AUTH)
                    else
                        startActivityModule(NavigationModule.GOTO_INTRO)

                finish()
            })
        }, LOADING_TIME)

    }
}