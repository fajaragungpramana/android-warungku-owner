package com.implizstudio.android.app.product.ui.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.implizstudio.android.app.components.field.UnitField
import com.implizstudio.android.app.product.R
import com.implizstudio.android.app.product.databinding.FragmentAddProductBinding
import com.implizstudio.android.app.resources.base.FragmentBase
import java.text.SimpleDateFormat
import java.util.*

class ProductAddFragment : FragmentBase<FragmentAddProductBinding>() {

    private val calendar = Calendar.getInstance()
    private val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        calendar.apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
        getBinding()?.tieAddExpired?.setText(
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)
        )
    }

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

            tieAddExpired.setOnClickListener {
                DatePickerDialog(
                    requireActivity(),
                    date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        }

    }

}