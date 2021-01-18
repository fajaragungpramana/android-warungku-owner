package com.implizstudio.android.app.auth.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.implizstudio.android.app.androidwarungkuowner.data.model.constant.Constant
import com.implizstudio.android.app.androidwarungkuowner.data.repository.auth.AuthRepositoryImpl
import com.implizstudio.android.app.androidwarungkuowner.di.feature.AuthDependency
import com.implizstudio.android.app.auth.R
import com.implizstudio.android.app.auth.databinding.FragmentLoginBinding
import com.implizstudio.android.app.auth.di.DaggerAuthComponent
import com.implizstudio.android.app.components.field.FormField
import com.implizstudio.android.app.resources.base.FragmentBase
import com.implizstudio.android.app.resources.extension.invisible
import com.implizstudio.android.app.resources.extension.setDataToSharedPreference
import com.implizstudio.android.app.resources.extension.startActivityModule
import com.implizstudio.android.app.resources.extension.visible
import com.implizstudio.android.app.resources.util.NavigationModule
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class LoginFragment : FragmentBase<FragmentLoginBinding>() {

    private val viewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var authRepositoryImpl: AuthRepositoryImpl

    private var isValidEmail = false
    private var isValidPassword = false

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
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        val bundle = Bundle()

        viewModel.apply {

            progressEnable.observe(viewLifecycleOwner, { isEnable ->
                getBinding()?.let {
                    if (isEnable) {
                        it.acbLogin.isEnabled = false
                        it.pbLoginProgress.visible()
                    } else {
                        it.acbLogin.isEnabled = true
                        it.pbLoginProgress.invisible()
                    }
                }
            })

            auth.observe(viewLifecycleOwner, { auth ->
                bundle.putParcelable(Constant.Key.AUTH, auth)
                requireActivity().setDataToSharedPreference(Constant.Key.ACCOUNT_ID to auth.id)
            })

            httpResponseCode.observe(viewLifecycleOwner, { code ->
                when (code) {

                    Constant.HttpCode.OK -> requireActivity().let {
                        it.setDataToSharedPreference(Constant.Key.IS_LOG_IN to true)
                        it.startActivityModule(NavigationModule.GOTO_DASHBOARD)
                        it.finish()
                    }

                    Constant.HttpCode.ACCEPTED ->
                        findNavController().navigate(R.id.action_login_to_verification, bundle)

                    Constant.HttpCode.NOT_FOUND ->
                        getBinding()?.ffLoginEmail?.errorMessage(R.string.error_email_not_registered)

                    Constant.HttpCode.FORBIDDEN ->
                        getBinding()?.ffLoginPassword?.errorMessage(R.string.error_wrong_password)

                }
            })

        }

        getBinding()?.apply {

            ivLoginIconBack.setOnClickListener { findNavController().navigateUp() }

            ffLoginEmail.let {
                it.onTextListener = object : FormField.OnTextListener {
                    override fun onTextChanged(text: CharSequence?) {
                        isValidEmail =
                            if (text != null && Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                                it.errorMessage(null)
                                true
                            } else {
                                it.errorMessage(R.string.error_email_format)
                                false
                            }
                        isValidToLogin()
                    }
                }
            }

            ffLoginPassword.let {
                it.onTextListener = object : FormField.OnTextListener {
                    override fun onTextChanged(text: CharSequence?) {
                        isValidPassword =
                            if (text != null && text.isNotEmpty()) {
                                it.errorMessage(null)
                                true
                            } else {
                                it.errorMessage(R.string.error_password_empty)
                                false
                            }
                        isValidToLogin()
                    }
                }
            }

            acbLogin.setOnClickListener {
                viewModel.doOwnerAccountAuthentication(authRepositoryImpl, mutableMapOf(
                    "email" to ffLoginEmail.getText(),
                    "password" to ffLoginPassword.getText()
                ))
            }

        }

    }

    private fun isValidToLogin() {
        if (isValidEmail && isValidPassword)
            setButtonLoginState(true, R.drawable.bg_button_primary)
        else
            setButtonLoginState(false, R.drawable.bg_button_disable)
    }

    private fun setButtonLoginState(enable: Boolean, backgroundId: Int) {
        getBinding()?.acbLogin.let {
            it?.isEnabled = enable
            it?.background = ContextCompat.getDrawable(requireActivity(), backgroundId)
        }
    }

}