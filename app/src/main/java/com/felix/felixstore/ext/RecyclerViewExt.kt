package com.felix.felixstore.ext

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: RecyclerViewExt
 */
val RecyclerView.lastUnVisibleCount: Int
    get() = kotlin.run {
        val layoutManager = layoutManager as LinearLayoutManager
        //屏幕中最后一个可见子项的position
        //屏幕中最后一个可见子项的position
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        //当前屏幕所看到的子项个数
        //当前屏幕所看到的子项个数
        val visibleItemCount = layoutManager.childCount
        //当前RecyclerView的所有子项个数
        //当前RecyclerView的所有子项个数
        val totalItemCount = layoutManager.itemCount
        //RecyclerView的滑动状态
        //RecyclerView的滑动状态
        val state: Int = this.scrollState
        takeIf { visibleItemCount > 0 }?.let {
            totalItemCount - lastVisibleItemPosition
        } ?: 0
    }