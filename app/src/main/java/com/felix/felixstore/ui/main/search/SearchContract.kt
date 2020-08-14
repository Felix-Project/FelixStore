package com.felix.felixstore.ui.main.search

import com.felix.felixstore.base.mvpvm.IBasePresenter
import com.felix.felixstore.base.mvpvm.IBaseView

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: HomeContract
 */
interface SearchView : IBaseView {
}

interface SearchPresenter : IBasePresenter {
    fun getSearch(keyword: String)
}
