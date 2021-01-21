package com.implizstudio.android.app.product.ui.add

import android.Manifest
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.implizstudio.android.app.androidwarungkuowner.data.model.constant.Constant
import com.implizstudio.android.app.components.field.PercentageField
import com.implizstudio.android.app.components.field.UnitField
import com.implizstudio.android.app.product.R
import com.implizstudio.android.app.product.databinding.FragmentAddProductBinding
import com.implizstudio.android.app.resources.base.FragmentBase
import com.implizstudio.android.app.resources.util.Permission
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                        ufAddPurchasePrice.setUnitText(value)
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

            ufAddPurchasePrice.let {
                it.setUnitText(availableList[0])
                it.valueListener = object : UnitField.ValueListener {
                    override fun getValue(value: Long) {
                        val sellPrice = pfAddSellPrice.getValue()

                        if (sellPrice.isNotEmpty()) {
                            val sellPriceValue = sellPrice.toLong()

                            if (sellPriceValue != 0L) pfAddSellPrice.setPercentage(
                                sellPriceValue,
                                value
                            )
                        }
                    }
                }
            }

            pfAddSellPrice.percentageListener = object : PercentageField.PercentageListener {
                override fun getValue(value: Long) {
                    val purchasePrice = ufAddPurchasePrice.getValue()

                    if (purchasePrice.isNotEmpty()) {
                        val purchasePriceValue = purchasePrice.toLong()

                        if (purchasePriceValue != 0L)
                            pfAddSellPrice.setPercentage(value, purchasePriceValue)
                    }
                }
            }

            tieAddBarcode.setOnClickListener {
                if (Permission.isCameraGranted(requireActivity())) {
                    val dialogScanner = layoutInflater.inflate(
                        R.layout.dialog_barcode_scanner,
                        null
                    )
                    val codeScannerView = dialogScanner.findViewById<CodeScannerView>(R.id.csv_barcode_scan)
                    val codeScanner = CodeScanner(requireActivity(), codeScannerView)

                    val alertDialog = AlertDialog.Builder(requireActivity())
                        .setView(dialogScanner)
                        .setOnDismissListener { codeScanner.stopPreview() }
                        .show()

                    codeScanner.setDecodeCallback {
                        CoroutineScope(Dispatchers.Main).launch {
                            tieAddBarcode.setText(it.text)
                            alertDialog.dismiss()
                        }
                    }

                    codeScanner.startPreview()
                } else
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.CAMERA),
                        Constant.RequestCode.CAMERA
                    )
            }

        }

    }

}