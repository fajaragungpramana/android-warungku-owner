package com.implizstudio.android.app.product.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.implizstudio.android.app.components.field.UnitField
import com.implizstudio.android.app.product.R
import com.implizstudio.android.app.product.databinding.FragmentAddProductBinding
import com.implizstudio.android.app.resources.base.FragmentBase

class ProductAddFragment : FragmentBase<FragmentAddProductBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAddProductBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {
        val availableList = resources.getStringArray(R.array.available_unit)

        getBinding()?.apply {

            ivAddIconBack.setOnClickListener { findNavController().navigateUp() }

            ufAddWeight.setUnitList(resources.getStringArray(R.array.weight_unit))

            ufAddAvailable.let {
                it.setUnitList(availableList)
                it.unitListener = object : UnitField.UnitListener {
                    override fun getUnitText(value: String) {
                        ufAddMinOrder.setUnitText(value)
                    }
                }
            }
            ufAddMinOrder.setUnitText(availableList[0])

        }

    }

}