package com.implizstudio.android.app.dashboard.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.implizstudio.android.app.dashboard.R
import com.implizstudio.android.app.dashboard.databinding.FragmentMessageBinding
import com.implizstudio.android.app.resources.base.FragmentBase

class MessageFragment : FragmentBase<FragmentMessageBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMessageBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        getBinding()?.apply {

            tMessageToolbar.title = getString(R.string.message)

        }

    }

}