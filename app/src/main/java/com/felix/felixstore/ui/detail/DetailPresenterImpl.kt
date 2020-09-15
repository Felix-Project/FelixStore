package com.felix.felixstore.ui.detail

import com.felix.felixstore.rx.ext.RxNet
import com.felix.felixstore.rx.ext.subscribeEmpty
import com.felix.lib_arch.mvpvm.AbsBasePresenter
import com.felix.lib_store.base.service.ApiDelegate

class DetailPresenterImpl : AbsBasePresenter<DetailView, DetailViewModel>(), DetailPresenter {
    override fun getAppDetail(pkgName: String) {
        ApiDelegate.getAppDetail(pkgName).subscribeOn(RxNet).doOnNext {
            viewModel.appDetailBean.postValue(it)
        }.subscribeEmpty()
    }

}