package com.implizstudio.android.app.auth.ui.verification

import `in`.aabhasjindal.otptextview.OTPListener
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.implizstudio.android.app.androidwarungkuowner.data.model.Auth
import com.implizstudio.android.app.androidwarungkuowner.data.model.Token
import com.implizstudio.android.app.androidwarungkuowner.data.model.constant.Constant
import com.implizstudio.android.app.androidwarungkuowner.data.repository.auth.AuthRepositoryImpl
import com.implizstudio.android.app.androidwarungkuowner.di.feature.AuthDependency
import com.implizstudio.android.app.auth.R
import com.implizstudio.android.app.auth.databinding.FragmentVerificationBinding
import com.implizstudio.android.app.auth.di.DaggerAuthComponent
import com.implizstudio.android.app.auth.service.CountDownTimerService
import com.implizstudio.android.app.resources.base.FragmentBase
import com.implizstudio.android.app.resources.extension.*
import com.implizstudio.android.app.resources.util.NavigationModule
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class VerificationFragment : FragmentBase<FragmentVerificationBinding>() {

    private val viewModel by viewModels<VerificationViewModel>()

    @Inject
    lateinit var authRepositoryImpl: AuthRepositoryImpl

    private val broadCastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {
            val isCountTimerFinished = intent.getBooleanExtra(Constant.Key.COUNT_FINISH, false)

            getBinding()?.tvVerificationResending?.apply {
                if (!isCountTimerFinished) {
                    val countTimerUtilFinished = intent.getLongExtra(Constant.Key.COUNT_TIMER, 0)
                    val minutes = (countTimerUtilFinished / 1000) / 60
                    val seconds = (countTimerUtilFinished / 1000) % 60

                    val time = "$minutes:$seconds"

                    text = time
                    isEnabled = false
                } else {
                    context?.stopService(Intent(context, CountDownTimerService::class.java))
                    text = getString(R.string.resending)
                    isEnabled = true
                }
            }
        }

    }

    override fun onAttach(context: Context) {
        DaggerAuthComponent.builder()
            .context(context)
            .appDependency(
                EntryPointAccessors.fromApplication(
                    requireActivity(),
                    AuthDependency::class.java
                )
            ).build()
            .inject(this)

        super.onAttach(context)
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentVerificationBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {
        val auth = arguments?.getParcelable<Auth>(Constant.Key.AUTH)
        var token = Token(auth?.auth?.token)

        viewModel.apply {

            progressEnable.observe(viewLifecycleOwner, { isEnable ->
                getBinding()?.let {
                    if (isEnable) {
                        it.acbVerification.isEnabled = false
                        it.pbVerificationProgress.visible()
                    } else {
                        it.acbVerification.isEnabled = true
                        it.pbVerificationProgress.invisible()
                    }
                }
            })

            httpResponseCode.observe(viewLifecycleOwner, { code ->
                when (code) {

                    Constant.HttpCode.OK -> requireActivity().let {
                        it.setDataToSharedPreference(Constant.Key.IS_LOG_IN to true)
                        it.startActivityModule(NavigationModule.GOTO_DASHBOARD)
                        it.finish()
                    }

                    Constant.HttpCode.UNAUTHORIZED ->
                        requireActivity().toast(R.string.error_wrong_code)

                    Constant.HttpCode.FORBIDDEN ->
                        requireActivity().toast(R.string.error_token_exp)

                }
            })

            accessToken.observe(viewLifecycleOwner, { token = it })

        }

        getBinding()?.apply {

            ivVerificationIconBack.setOnClickListener {
                findNavController().popBackStack(R.id.started, false)
            }

            tvVerificationEmail.text = auth?.email

            tvVerificationResending.setOnClickListener {
                viewModel.getEmailCodeVerification(
                    authRepositoryImpl, mutableMapOf(
                        "owner_id" to auth?.id,
                        "name_recipient" to auth?.fullName,
                        "mail_recipient" to auth?.email
                    )
                )
                requireActivity().let {
                    it.startService(Intent(it, CountDownTimerService::class.java))
                }
            }

            otvVerification.otpListener = object : OTPListener {
                override fun onInteractionListener() {
                    setButtonVerificationState(false, R.drawable.bg_button_disable)
                }

                override fun onOTPComplete(otp: String?) {
                    setButtonVerificationState(true, R.drawable.bg_button_primary)
                    acbVerification.setOnClickListener {
                        viewModel.doEmailVerification(
                            authRepositoryImpl,
                            mutableMapOf(
                                "owner_id" to auth?.id,
                                "code" to otp
                            ),
                            token.token
                        )
                    }
                }
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().popBackStack(R.id.started, false)
        }

    }

    override fun onResume() {
        requireActivity().registerReceiver(
            broadCastReceiver,
            IntentFilter(CountDownTimerService.INTENT_ACTION)
        )
        super.onResume()
    }

    override fun onPause() {
        requireActivity().unregisterReceiver(broadCastReceiver)
        super.onPause()
    }

    override fun onDestroy() {
        requireActivity().apply {
            this.stopService(Intent(this, CountDownTimerService::class.java))
        }
        super.onDestroy()
    }

    private fun setButtonVerificationState(enable: Boolean, backgroundId: Int) {
        getBinding()?.acbVerification?.let {
            it.isEnabled = enable
            it.background = ContextCompat.getDrawable(requireActivity(), backgroundId)
        }
    }

}