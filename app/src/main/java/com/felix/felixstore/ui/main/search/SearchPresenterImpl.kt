package com.felix.felixstore.ui.main.search

import com.felix.felixstore.base.mvpvm.AbsBasePresenter
import com.felix.felixstore.rx.ext.RxMain
import com.felix.felixstore.rx.ext.RxNet
import com.felix.felixstore.rx.ext.subscribeEmpty
import com.felix.lib_store.base.service.ApiDegelate

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: HomePresenterImpl
 */
class SearchPresenterImpl : AbsBasePresenter<SearchView, SearchViewModel>(), SearchPresenter {
    override fun getSearch(keyword: String) {
        ApiDegelate.getAppItem(keyword, 1).subscribeOn(RxNet).doOnNext { appList ->
            viewModel.hotAppList.let {
                it.value?.clear()
                it.addValue(appList)
            }
        }.subscribeEmpty()
    }
}