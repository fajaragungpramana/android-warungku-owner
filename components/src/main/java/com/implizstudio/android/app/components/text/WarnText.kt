package com.implizstudio.android.app.components.text

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.implizstudio.android.app.components.R
import com.implizstudio.android.app.components.databinding.TextWarnBinding

class WarnText @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyle, defStyleRes) {

    init {
        val viewBinding = TextWarnBinding.inflate(LayoutInflater.from(ctx), this, true)

        val typedArray = ctx.obtainStyledAttributes(
            attrs,
            R.styleable.WarnText,
            defStyle,
            defStyleRes
        )

        val warnMessage = typedArray.getResourceId(R.styleable.WarnText_message, 0)

        viewBinding.tvWarnMessage.text = ctx.getString(warnMessage)

        typedArray.recycle()
    }

}