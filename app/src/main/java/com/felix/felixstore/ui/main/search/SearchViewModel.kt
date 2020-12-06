package com.felix.felixstore.ui.main.search

import com.felix.felixstore.rx.ext.RxNet
import com.felix.felixstore.rx.ext.subscribeEmpty
import com.felix.arch.mvvm.BaseViewModel
import com.felix.arch.mvvm.ListLiveData
import com.felix.lib_store.base.bean.AppItem
import com.felix.lib_store.base.service.ApiDelegate

class SearchViewModel : BaseViewModel() {
    val hotAppList = ListLiveData<AppItem>()

    fun getSearch(keyword: String) {
        ApiDelegate.getAppItem(keyword, 1).subscribeOn(RxNet).doOnNext { appList ->
            hotAppList.let {
                it.value?.clear()
                it.addValue(appList)
            }
        }.subscribeEmpty()
    }

    fun getDefAppList() {
        ApiDelegate.getTopList(1).subscribeOn(RxNet).doOnNext { appList ->
            hotAppList.let {
                it.value?.clear()
                it.addValue(appList)
            }
        }.subscribeEmpty()
    }
}