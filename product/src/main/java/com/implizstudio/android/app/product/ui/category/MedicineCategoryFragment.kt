package com.implizstudio.android.app.product.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.implizstudio.android.app.product.databinding.FragmentCategoryBinding
import com.implizstudio.android.app.resources.base.FragmentBase

class MedicineCategoryFragment : FragmentBase<FragmentCategoryBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCategoryBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}