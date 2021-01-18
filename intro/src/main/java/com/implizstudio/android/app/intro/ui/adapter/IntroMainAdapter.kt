package com.implizstudio.android.app.intro.ui.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.implizstudio.android.app.intro.R
import com.implizstudio.android.app.intro.databinding.AdapterMainIntroBinding

class IntroMainAdapter(private val ctx: Context) : PagerAdapter() {

    private val introImages: TypedArray
        get() = ctx.resources.obtainTypedArray(R.array.intro_images)

    private val introTitles: Array<String>
        get() = ctx.resources.getStringArray(R.array.intro_titles)

    private val introMessages: Array<String>
        get() = ctx.resources.getStringArray(R.array.intro_messages)

    override fun isViewFromObject(view: View, `object`: Any) = view == `object` as ConstraintLayout

    override fun getCount() = introTitles.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = AdapterMainIntroBinding.inflate(LayoutInflater.from(ctx), container, false)
        val view = binding.root

        container.addView(view)

        binding.apply {

            ivIntroImage.setImageResource(introImages.getResourceId(position, 0))
            tvIntroTitle.text = introTitles[position]
            tvIntroMessage.text = introMessages[position]

            ivIntroImage.startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.bounce))

            tvIntroTitle.let {
                it.alpha = 0f
                it.translationY = 100f
                it.animate().alpha(1f).translationY(0f).setStartDelay(300).setDuration(600).start()
            }

            tvIntroMessage.let {
                it.alpha = 0f
                it.translationY = 100f
                it.animate().alpha(1f).translationY(0f).setStartDelay(600).setDuration(600).start()
            }

        }

        introImages.recycle()

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}