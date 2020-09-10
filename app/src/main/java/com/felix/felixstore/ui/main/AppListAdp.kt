package com.felix.felixstore.ui.main

import android.view.View
import com.bumptech.glide.Glide
import com.felix.felixstore.R
import com.felix.lib_arch.adp.BaseAdp
import com.felix.lib_store.base.bean.AppItem
import kotlinx.android.synthetic.main.common_app_item.view.*

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: AppListAdp
 */
class AppListAdp : BaseAdp<AppItem>() {
    override val layoutId: Int = R.layout.common_app_item

    override fun onDataChange(view: View, appItem: AppItem, pos: Int, size: Int) {
        view.apply {
            Glide.with(ivAppIcon).load(appItem.appIcon).into(ivAppIcon)
            tvAppName.text = appItem.appName
            tvAppCategory.text = appItem.appCategoryName
        }
    }
}