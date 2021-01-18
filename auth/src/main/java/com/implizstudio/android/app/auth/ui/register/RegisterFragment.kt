package com.implizstudio.android.app.auth.ui.register

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
import com.implizstudio.android.app.auth.databinding.FragmentRegisterBinding
import com.implizstudio.android.app.auth.di.DaggerAuthComponent
import com.implizstudio.android.app.components.field.FormField
import com.implizstudio.android.app.resources.base.FragmentBase
import com.implizstudio.android.app.resources.extension.invisible
import com.implizstudio.android.app.resources.extension.setDataToSharedPreference
import com.implizstudio.android.app.resources.extension.visible
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class RegisterFragment : FragmentBase<FragmentRegisterBinding>() {

    private val viewModel by viewModels<RegisterViewModel>()

    @Inject
    lateinit var authRepositoryImpl: AuthRepositoryImpl

    private var isValidName = false
    private var isValidStore = false
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
        FragmentRegisterBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        val bundle = Bundle()

        viewModel.apply {

            progressEnable.observe(viewLifecycleOwner, { isEnable ->
                getBinding()?.let {
                    if (isEnable) {
                        it.acbRegister.isEnabled = false
                        it.pbRegisterProgress.visible()
                    } else {
                        it.acbRegister.isEnabled = true
                        it.pbRegisterProgress.invisible()
                    }
                }
            })

            auth.observe(viewLifecycleOwner, { auth ->
                bundle.putParcelable(Constant.Key.AUTH, auth)
                requireActivity().setDataToSharedPreference(Constant.Key.ACCOUNT_ID to auth.id)
            })

            httpResponseCode.observe(viewLifecycleOwner, { code ->
                when (code) {

                    Constant.HttpCode.CREATED ->
                        findNavController().navigate(R.id.action_register_to_verification, bundle)

                    Constant.HttpCode.FORBIDDEN ->
                        getBinding()?.ffRegisterEmail?.errorMessage(R.string.error_email_has_been_registered)

                }
            })

        }

        getBinding()?.apply {

            ivRegisterIconBack.setOnClickListener { findNavController().navigateUp() }

            ffRegisterName.let {
                it.onTextListener = object : FormField.OnTextListener {
                    override fun onTextChanged(text: CharSequence?) {
                        isValidName =
                            if (text != null && text.length > 3) {
                                it.errorMessage(null)
                                true
                            } else {
                                it.errorMessage(R.string.error_name)
                                false
                            }
                        isValidToRegister()
                    }
                }
            }

            ffRegisterStore.let {
                it.onTextListener = object : FormField.OnTextListener {
                    override fun onTextChanged(text: CharSequence?) {
                        isValidStore =
                            if (text != null && text.length > 3) {
                                it.errorMessage(null)
                                true
                            } else {
                                it.errorMessage(R.string.error_name)
                                false
                            }
                        isValidToRegister()
                    }
                }
            }

            ffRegisterEmail.let {
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
                        isValidToRegister()
                    }
                }
            }

            ffRegisterPassword.let {
                it.onTextListener = object : FormField.OnTextListener {
                    override fun onTextChanged(text: CharSequence?) {
                        isValidPassword =
                            if (text != null && text.length > 7) {
                                it.errorMessage(null)
                                true
                            } else {
                                it.errorMessage(R.string.error_password_minus)
                                false
                            }
                        isValidToRegister()
                    }
                }
            }

            acbRegister.setOnClickListener {
                viewModel.doOwnerAccountRegistration(
                    authRepositoryImpl, mutableMapOf(
                        "full_name" to ffRegisterName.getText(),
                        "store_name" to ffRegisterStore.getText(),
                        "email" to ffRegisterEmail.getText(),
                        "password" to ffRegisterPassword.getText()
                    )
                )
            }

        }

    }

    private fun isValidToRegister() {
        if (isValidName && isValidStore && isValidEmail && isValidPassword)
            setButtonRegisterState(true, R.drawable.bg_button_primary)
        else
            setButtonRegisterState(false, R.drawable.bg_button_disable)
    }

    private fun setButtonRegisterState(enable: Boolean, backgroundId: Int) {
        getBinding()?.acbRegister.let {
            it?.isEnabled = enable
            it?.background = ContextCompat.getDrawable(requireActivity(), backgroundId)
        }
    }

}