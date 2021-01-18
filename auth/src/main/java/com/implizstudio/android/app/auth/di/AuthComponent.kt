package com.implizstudio.android.app.auth.di

import android.content.Context
import com.implizstudio.android.app.androidwarungkuowner.di.feature.AuthDependency
import com.implizstudio.android.app.auth.ui.login.LoginFragment
import com.implizstudio.android.app.auth.ui.register.RegisterFragment
import com.implizstudio.android.app.auth.ui.verification.VerificationFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AuthDependency::class])
interface AuthComponent {

    fun inject(registerFragment: RegisterFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(verificationFragment: VerificationFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependency(authDependency: AuthDependency): Builder
        fun build(): AuthComponent
    }

}