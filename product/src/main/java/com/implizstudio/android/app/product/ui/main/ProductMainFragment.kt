package com.implizstudio.android.app.product.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.implizstudio.android.app.product.R
import com.implizstudio.android.app.product.databinding.FragmentMainProductBinding
import com.implizstudio.android.app.product.ui.adapter.TabAdapter
import com.implizstudio.android.app.product.ui.category.*
import com.implizstudio.android.app.resources.base.FragmentBase

class ProductMainFragment : FragmentBase<FragmentMainProductBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainProductBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        val tabAdapter = TabAdapter(childFragmentManager).apply {
            set(AllCategoryFragment(), getString(R.string.all))
            set(FoodCategoryFragment(), getString(R.string.food))
            set(DrinkCategoryFragment(), getString(R.string.drink))
            set(MedicineCategoryFragment(), getString(R.string.medicine))
            set(ToolCategoryFragment(), getString(R.string.tool))
        }

        getBinding()?.apply {

            ivMainIconBack.setOnClickListener { requireActivity().finish() }

            vpMainProduct.adapter = tabAdapter

            tlMainCategory.setupWithViewPager(vpMainProduct)

            fabMainToAdd.setOnClickListener { findNavController().navigate(R.id.action_main_to_add) }

        }

    }

}