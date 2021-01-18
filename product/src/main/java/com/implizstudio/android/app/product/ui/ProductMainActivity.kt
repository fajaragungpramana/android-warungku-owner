package com.implizstudio.android.app.product.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.implizstudio.android.app.product.R
import com.implizstudio.android.app.product.databinding.ActivityMainProductBinding
import com.implizstudio.android.app.resources.base.ActivityBase

class ProductMainActivity : ActivityBase() {

    override fun onCreated(savedInstanceState: Bundle?) {
        val binding = ActivityMainProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.findNavController(this, R.id.f_main_product)

    }

}