package com.implizstudio.android.app.resources.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class AdapterBase<V : ViewBinding, H : RecyclerView.ViewHolder, E>(
    private val list: List<E>
) : RecyclerView.Adapter<H>() {

    private lateinit var binding: V

    protected abstract fun getViewBinding(parent: ViewGroup): V

    protected abstract fun getViewHolder(view: View): H

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        binding = getViewBinding(parent)
        return getViewHolder(binding.root)
    }

    protected fun getBinding() = binding

}