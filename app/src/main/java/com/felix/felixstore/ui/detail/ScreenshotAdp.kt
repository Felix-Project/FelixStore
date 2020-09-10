package com.felix.felixstore.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.felix.felixstore.R
import com.felix.felixstore.databinding.DetailScreenshotItemBinding
import com.felix.lib_arch.adp.BaseAdp
import kotlinx.android.synthetic.main.detail_screenshot_item.view.*

class ScreenshotAdp : BaseAdp<String>() {
    override val layoutId = R.layout.detail_screenshot_item

    override fun onDataChange(view: View, data: String, pos: Int, size: Int) {
        view.apply {
            Glide.with(context).load(data).into(ivScreenshot)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        val bind: ViewBinding = DetailScreenshotItemBinding.bind(parent)
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        super.onBindViewHolder(holder, position)

    }
}