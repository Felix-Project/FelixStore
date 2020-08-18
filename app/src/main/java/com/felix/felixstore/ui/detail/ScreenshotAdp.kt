package com.felix.felixstore.ui.detail

import android.view.View
import com.bumptech.glide.Glide
import com.felix.felixstore.R
import com.felix.lib_arch.adp.BaseAdp
import kotlinx.android.synthetic.main.detail_screenshot_item.view.*

class ScreenshotAdp : BaseAdp<String>() {
    override val layoutId= R.layout.detail_screenshot_item

    override val onDataChangeListenner: (View, String, Int, Int) -> Unit
        get() = {view, url, pos, size ->
            view.apply {
                Glide.with(context).load(url).into(ivScreenshot)
            }
        }
}