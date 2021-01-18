package com.implizstudio.android.app.dashboard.ui

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.implizstudio.android.app.dashboard.R
import com.implizstudio.android.app.dashboard.databinding.ActivityMainDashboardBinding
import com.implizstudio.android.app.resources.base.ActivityBase

class DashboardMainActivity : ActivityBase() {

    override fun onCreated(savedInstanceState: Bundle?) {
        val binding = ActivityMainDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvDashboardMenu.setupWithNavController(Navigation.findNavController(this, R.id.f_dashboard))

    }

}