package com.felix.felixstore.ui.main.hot

import com.felix.felixstore.base.mvpvm.IBasePresenter
import com.felix.felixstore.base.mvpvm.IBaseView

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: HomeContract
 */
interface HotView : IBaseView {
    fun onPageLoad(currentPage: Int, allPage: Int)
    fun onloadError(throwable: Throwable)
}

interface HotPresenter : IBasePresenter {
    fun getHotAppList()
    fun getNextHotAppList()
}
