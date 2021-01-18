package com.implizstudio.android.app.auth.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.implizstudio.android.app.auth.R
import com.implizstudio.android.app.auth.databinding.ActivityMainAuthBinding
import com.implizstudio.android.app.resources.base.ActivityBase

class AuthMainActivity : ActivityBase() {

    override fun onCreated(savedInstanceState: Bundle?) {
        val binding = ActivityMainAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.findNavController(this, R.id.f_auth)

    }

}