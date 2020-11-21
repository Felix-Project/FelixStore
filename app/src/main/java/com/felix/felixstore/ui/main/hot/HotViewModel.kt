package com.felix.felixstore.ui.main.hot

import com.felix.felixstore.rx.ext.RxMain
import com.felix.felixstore.rx.ext.RxNet
import com.felix.felixstore.rx.ext.subscribeEmpty
import com.felix.lib_arch.mvvm.BaseViewModel
import com.felix.lib_arch.mvvm.ListLiveData
import com.felix.lib_store.base.bean.AppItem
import com.felix.lib_store.base.service.ApiDelegate

class HotViewModel : BaseViewModel() {
    val hotAppList = ListLiveData<AppItem>()


    private var nextPage = 1
    fun getHotAppList() {
        hotAppList.value?.clear()
        nextPage = 1
        getNextHotAppList()
    }

    fun getNextHotAppList() {
        ApiDelegate.getTopList(nextPage).subscribeOn(RxNet).doOnNext { appList ->
            hotAppList.addValue(appList)
            nextPage++
        }.observeOn(RxMain).subscribeEmpty()
    }
}