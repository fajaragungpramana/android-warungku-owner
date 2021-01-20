package com.implizstudio.android.app.components.field

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.implizstudio.android.app.components.R
import com.implizstudio.android.app.components.databinding.FieldUnitBinding

class UnitField @JvmOverloads constructor(
    private val ctx: Context,
    attrs: AttributeSet,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(ctx, attrs, defStyle, defStyleRes) {

    private val viewBinding = FieldUnitBinding.inflate(LayoutInflater.from(ctx), this, true)

    lateinit var unitListener: UnitListener
    lateinit var valueListener: ValueListener

    private val unitList = mutableListOf<String>()
    private var position = 0

    init {
        val typedArray = ctx.obtainStyledAttributes(
            attrs,
            R.styleable.UnitField,
            defStyle,
            defStyleRes
        )

        val isClickable = typedArray.getBoolean(R.styleable.UnitField_isClickable, false)
        val hint = typedArray.getString(R.styleable.UnitField_hintUnit)

        viewBinding.apply {
            tilUnit.hint = hint
            tieUnit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (::valueListener.isInitialized)
                        if (s != null && s.isNotEmpty()) {
                            val value = s.toString().toLong()
                            if (value != 0L) valueListener.getValue(value)
                        }
                }
            })
        }

        setUnitClickable(isClickable)

        typedArray.recycle()
    }

    private fun setUnitClickable(isClickable: Boolean) {
        if (isClickable) {
            setUnitBackground(R.drawable.bg_unit_unactive)

            viewBinding.apply {
                tieUnit.setOnFocusChangeListener { _, isFocus ->
                    tvUnit.isEnabled = isFocus

                    if (isFocus) {
                        setUnitBackground(R.drawable.bg_unit_active)

                        tvUnit.setOnClickListener {
                            if (position < unitList.size - 1)
                                position += 1
                            else
                                position = 0

                            setUnitText(unitList[position])

                            if (::unitListener.isInitialized) {
                                unitListener.getUnitText(unitList[position])
                            }
                        }
                    } else
                        setUnitBackground(R.drawable.bg_unit_unactive)
                }

            }
        }
    }

    private fun setUnitBackground(restId: Int) {
        viewBinding.tvUnit.apply {
            background = ContextCompat.getDrawable(ctx, restId)
            setTextColor(ContextCompat.getColor(ctx, android.R.color.white))
        }
    }

    fun errorMessage(restId: Int?) {
        viewBinding.tilUnit.error = if (restId != null) resources.getString(restId) else null
    }

    fun setUnitText(value: String) {
        viewBinding.tvUnit.text = value
    }

    fun getValue() = viewBinding.tieUnit.text.toString()

    fun getUnitText() = viewBinding.tvUnit.text.toString()

    fun setUnitList(list: Array<String>) {
        unitList.addAll(list)

        setUnitText(unitList[position])
    }

    interface UnitListener {
        fun getUnitText(value: String)
    }

    interface ValueListener {
        fun getValue(value: Long)
    }

}