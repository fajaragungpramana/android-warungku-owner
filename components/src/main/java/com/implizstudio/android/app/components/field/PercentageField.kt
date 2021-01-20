package com.implizstudio.android.app.components.field

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.implizstudio.android.app.components.R
import com.implizstudio.android.app.components.databinding.FieldPercentageBinding
import java.text.DecimalFormat

class PercentageField @JvmOverloads constructor(
    private val ctx: Context,
    attrs: AttributeSet,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(ctx, attrs, defStyle, defStyleRes) {

    private val viewBinding = FieldPercentageBinding.inflate(LayoutInflater.from(ctx), this, true)

    lateinit var percentageListener: PercentageListener

    init {
        val typedArray = ctx.obtainStyledAttributes(
            attrs,
            R.styleable.PercentageField,
            defStyle,
            defStyleRes
        )

        val hintField = typedArray.getString(R.styleable.PercentageField_hintPercentage)

        viewBinding.apply {
            tilPercentage.hint = hintField

            tiePercentage.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (::percentageListener.isInitialized)
                        if (s != null && s.isNotEmpty()) {
                            val value = s.toString().toLong()
                            if (value != 0L) percentageListener.getValue(value)
                        }
                }
            })

        }

        typedArray.recycle()
    }

    private fun setPercentageColor(resId: Int) {
        viewBinding.tvPercentage.setTextColor(ContextCompat.getColor(ctx, resId))
    }

    fun setPercentage(sellPrice: Long, purchasePrice: Long) {
        viewBinding.tvPercentage.text =
            if (sellPrice > purchasePrice) {
                val count = sellPrice - purchasePrice
                val percentage = (count * 100.0) / sellPrice

                setPercentageColor(R.color.color_on_primary)
                "+${DecimalFormat("##.##").format(percentage)}%"
            } else {
                val count = purchasePrice - sellPrice
                val percentage = (count * 100.0) / purchasePrice

                setPercentageColor(R.color.color_primary_error)
                "-${DecimalFormat("##.##").format(percentage)}%"
            }
    }

    fun errorMessage(resId: Int?) {
        viewBinding.tilPercentage.error = if (resId != null) resources.getString(resId) else null
    }

    fun getValue() = viewBinding.tiePercentage.text.toString()

    interface PercentageListener {
        fun getValue(value: Long)
    }

}