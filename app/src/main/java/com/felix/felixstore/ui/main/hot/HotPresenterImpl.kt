package com.felix.felixstore.ui.main.hot

import com.felix.lib_arch.mvpvm.AbsBasePresenter
import com.felix.felixstore.rx.ext.RxMain
import com.felix.felixstore.rx.ext.RxNet
import com.felix.felixstore.rx.ext.subscribeEmpty
import com.felix.lib_store.base.service.ApiDelegate

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: HomePresenterImpl
 */
class HotPresenterImpl : AbsBasePresenter<HotView, HotViewModel>(), HotPresenter {
    private var nextPage = 1
    override fun getHotAppList() {
        viewModel.hotAppList.value?.clear()
        nextPage = 1
        getNextHotAppList()
    }

    override fun getNextHotAppList() {
        ApiDelegate.getTopList(nextPage).subscribeOn(RxNet).also {

        }.doOnNext { appList ->
            viewModel.hotAppList.addValue(appList)
        }.observeOn(RxMain).doOnNext {
            view.onPageLoad(nextPage++, it.firstOrNull()?.allPage ?: -1)
        }.subscribeEmpty()
    }
}