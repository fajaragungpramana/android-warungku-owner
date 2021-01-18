package com.implizstudio.android.app.intro.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.viewpager.widget.ViewPager
import com.implizstudio.android.app.androidwarungkuowner.data.model.constant.Constant
import com.implizstudio.android.app.intro.R
import com.implizstudio.android.app.intro.databinding.ActivityMainIntroBinding
import com.implizstudio.android.app.intro.ui.adapter.IntroMainAdapter
import com.implizstudio.android.app.resources.base.ActivityBase
import com.implizstudio.android.app.resources.extension.setDataToSharedPreference
import com.implizstudio.android.app.resources.extension.startActivityModule
import com.implizstudio.android.app.resources.util.NavigationModule

class IntroMainActivity : ActivityBase() {

    private companion object {
        const val MAX_INTRO_PAGE = 3
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        val binding = ActivityMainIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            vpIntroPage.let {
                it.adapter = IntroMainAdapter(this@IntroMainActivity)
                it.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                    override fun onPageScrollStateChanged(state: Int) {}

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        acbIntroNext.let { btn ->
                            if (position == MAX_INTRO_PAGE) {
                                btn.text = getString(R.string.done)
                                btn.startAnimation(
                                    AnimationUtils.loadAnimation(
                                        this@IntroMainActivity,
                                        R.anim.bounce
                                    )
                                )
                            } else
                                btn.text = getString(R.string.next)
                        }
                    }

                })
            }

            diIntroIndicator.setViewPager(vpIntroPage)

            acbIntroNext.setOnClickListener {
                vpIntroPage.let {
                    if (it.currentItem < MAX_INTRO_PAGE)
                        it.currentItem += 1
                    else {
                        setDataToSharedPreference(Constant.Key.IS_INTRO to true)

                        startActivityModule(NavigationModule.GOTO_AUTH)
                        finish()
                    }
                }
            }

        }

    }

}