package com.implizstudio.android.app.components.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.implizstudio.android.app.components.R
import com.implizstudio.android.app.components.databinding.CardFeatureBinding

class CardFeature @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyle, defStyleRes) {

    init {
        val viewBinding = CardFeatureBinding.inflate(LayoutInflater.from(ctx), this, true)

        val typedArray = ctx.obtainStyledAttributes(
            attrs,
            R.styleable.CardFeature,
            defStyle,
            defStyleRes
        )

        val image = typedArray.getResourceId(R.styleable.CardFeature_image, 0)
        val name = typedArray.getResourceId(R.styleable.CardFeature_name, 0)

        viewBinding.apply {

            ivFeatureImage.setImageResource(image)

            tvFeatureName.text = resources.getString(name)

        }

        typedArray.recycle()
    }

}