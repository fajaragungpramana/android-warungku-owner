package com.implizstudio.android.app.product.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val listFragment = mutableListOf<Fragment>()
    private val listName = mutableListOf<String>()

    override fun getCount() = listFragment.size

    override fun getItem(position: Int) = listFragment[position]

    override fun getPageTitle(position: Int) = listName[position]

    fun set(fragment: Fragment, name: String) {
        listFragment.add(fragment)
        listName.add(name)
    }

}