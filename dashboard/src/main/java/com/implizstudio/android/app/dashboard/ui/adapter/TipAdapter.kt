package com.implizstudio.android.app.dashboard.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.implizstudio.android.app.androidwarungkuowner.data.model.Tip
import com.implizstudio.android.app.dashboard.R
import com.implizstudio.android.app.dashboard.databinding.AdapterTipBinding
import com.implizstudio.android.app.resources.base.AdapterBase
import com.implizstudio.android.app.resources.extension.imageUrl

class TipAdapter(private val ctx: Context, private val listTip: List<Tip>) :
    AdapterBase<AdapterTipBinding, TipAdapter.ViewHolder, Tip>(listTip) {

    override fun getViewBinding(parent: ViewGroup) =
        AdapterTipBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun getViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTip(listTip[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindTip(tip: Tip) {
            getBinding().apply {
                ivTipImage.imageUrl(tip.image, R.drawable.ic_no_image)
                tvTipTitle.text = tip.title
            }
        }

    }

}