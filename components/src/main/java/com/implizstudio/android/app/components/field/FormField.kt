package com.implizstudio.android.app.components.field

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.implizstudio.android.app.components.R
import com.implizstudio.android.app.components.databinding.FieldFormBinding

class FormField @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyle, defStyleRes) {

    private val viewBinding: FieldFormBinding = FieldFormBinding.inflate(
        LayoutInflater.from(ctx),
        this,
        true
    )

    lateinit var onTextListener: OnTextListener

    init {

        val typedArray = ctx.obtainStyledAttributes(
            attrs,
            R.styleable.FormField,
            defStyle,
            defStyleRes
        )

        val formHint = typedArray.getResourceId(R.styleable.FormField_hint, 0)
        val formInputType = typedArray.getInt(R.styleable.FormField_inputType, 0)
        val formPasswordToggleEnabled = typedArray.getBoolean(
            R.styleable.FormField_passwordToggleEnabled,
            false
        )

        viewBinding.let {
            it.tilForm.isPasswordVisibilityToggleEnabled = formPasswordToggleEnabled

            it.tieForm.apply {
                val textHint = resources.getString(formHint)

                hint = textHint
                inputType = formInputType

                onFocusChangeListener = OnFocusChangeListener { _, isFocus ->
                    if (isFocus) {
                        it.tilForm.hint = textHint
                        hint = null
                    } else {
                        if (text.let { t -> t != null && t.isEmpty() })
                            it.tilForm.hint = null

                        hint = textHint
                    }
                }

                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {}

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (::onTextListener.isInitialized) onTextListener.onTextChanged(s)
                    }
                })
            }
        }

        typedArray.recycle()
    }

    fun getText() = viewBinding.tieForm.text.toString()

    fun errorMessage(resId: Int?) {
        viewBinding.tilForm.error = if (resId != null) resources.getString(resId) else null
    }

    interface OnTextListener {
        fun onTextChanged(text: CharSequence?)
    }

}