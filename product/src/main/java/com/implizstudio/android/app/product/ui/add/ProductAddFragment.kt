package com.implizstudio.android.app.product.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.implizstudio.android.app.product.databinding.FragmentAddProductBinding
import com.implizstudio.android.app.resources.base.FragmentBase

class ProductAddFragment : FragmentBase<FragmentAddProductBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAddProductBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        getBinding()?.apply {

            ivAddIconBack.setOnClickListener { findNavController().navigateUp() }

        }

    }

}