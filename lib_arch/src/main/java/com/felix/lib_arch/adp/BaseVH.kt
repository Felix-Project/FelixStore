package com.felix.lib_arch.adp

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: BaseVH
 */
open class BaseVH<T>(view: View) : RecyclerView.ViewHolder(view) {
    open fun onDataChange(data: T, position: Int, size: Int) {

    }
}