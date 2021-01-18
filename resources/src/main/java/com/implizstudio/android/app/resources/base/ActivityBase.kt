package com.implizstudio.android.app.resources.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

abstract class ActivityBase : AppCompatActivity() {

    @Suppress("DEPRECATION")
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        val locale = Locale("in")
        Locale.setDefault(locale)

        resources.let {
            it.configuration.setLocale(locale)
            it.updateConfiguration(it.configuration, it.displayMetrics)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreated(savedInstanceState)
    }

    protected abstract fun onCreated(savedInstanceState: Bundle?)

}