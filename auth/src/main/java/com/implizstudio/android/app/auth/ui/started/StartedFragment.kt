package com.implizstudio.android.app.auth.ui.started

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.implizstudio.android.app.auth.R
import com.implizstudio.android.app.auth.databinding.FragmentStartedBinding
import com.implizstudio.android.app.resources.base.FragmentBase

class StartedFragment : FragmentBase<FragmentStartedBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentStartedBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        getBinding()?.apply {

            acbStartedGotoLogin.setOnClickListener { findNavController().navigate(R.id.action_started_to_login) }

            acbStartedGotoRegister.setOnClickListener { findNavController().navigate(R.id.action_started_to_register) }

        }


    }

}