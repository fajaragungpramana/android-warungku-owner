package com.implizstudio.android.app.dashboard.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.implizstudio.android.app.dashboard.R
import com.implizstudio.android.app.dashboard.databinding.FragmentProfileBinding
import com.implizstudio.android.app.resources.base.FragmentBase

class ProfileFragment : FragmentBase<FragmentProfileBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentProfileBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        getBinding()?.apply {

            tProfileToolbar.title = getString(R.string.profile)

        }

    }

}