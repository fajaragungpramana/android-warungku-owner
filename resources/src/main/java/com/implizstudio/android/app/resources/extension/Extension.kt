package com.implizstudio.android.app.resources.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import com.squareup.picasso.Picasso
import java.text.NumberFormat

private const val WARUNGKU_PREFERENCE = "warungku_owner_preference"

@Suppress("UNCHECKED_CAST")
fun Context.startActivityModule(path: String, vararg data: Pair<String, Any?>) {
    val intent = Intent().setClassName(this, path)

    if (data.isNotEmpty())
        data.forEach {
            when (it.second) {
                is String -> intent.putExtra(it.first, it.second as String)
                is Int -> intent.putExtra(it.first, it.second as Int)
                is Boolean -> intent.putExtra(it.first, it.second as Boolean)
                is Parcelable -> intent.putParcelableArrayListExtra(
                    it.first,
                    it.second as ArrayList<Parcelable>
                )
                else -> throw TypeCastException("Unknown data type passing, please check the extensions!")
            }
        }

    startActivity(intent)
}

fun Context.setDataToSharedPreference(vararg data: Pair<String, Any?>) {
    val sharedPreference = this.getSharedPreferences(WARUNGKU_PREFERENCE, Context.MODE_PRIVATE)

    if (data.isNotEmpty())
        data.forEach {
            when (it.second) {
                is String -> sharedPreference.edit { putString(it.first, it.second as String) }
                is Int -> sharedPreference.edit { putInt(it.first, it.second as Int) }
                is Boolean -> sharedPreference.edit { putBoolean(it.first, it.second as Boolean) }
                else -> throw TypeCastException("Unknown data type passing, please check the extensions!")
            }
        }
}

@Suppress("UNCHECKED_CAST")
fun <T> Context.getDataFromSharedPreference(key: String, defValue: T): T {
    val sharedPreference = this.getSharedPreferences(WARUNGKU_PREFERENCE, Context.MODE_PRIVATE)

    return when (defValue) {
        is String -> sharedPreference.getString(key, defValue)
        is Int -> sharedPreference.getInt(key, defValue)
        is Boolean -> sharedPreference.getBoolean(key, defValue)
        else -> throw TypeCastException("Unknown data type passing, please check the extensions!")
    } as T

}

fun Context.toast(resId: Int) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

@SuppressLint("SetTextI18n")
fun TextView.currency(amount: Long?) {
    val numberFormat = NumberFormat.getInstance().format(amount)

    text = if (amount != null) "Rp $numberFormat" else "Rp 0"
}

fun ImageView.imageUrl(url: String?, errorResId: Int) {
    Picasso.get()
        .load(url)
        .placeholder(errorResId)
        .error(errorResId)
        .into(this)
}