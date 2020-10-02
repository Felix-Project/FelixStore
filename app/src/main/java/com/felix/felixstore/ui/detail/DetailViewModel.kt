package com.felix.felixstore.ui.detail

import androidx.lifecycle.MutableLiveData
import com.felix.felixstore.rx.ext.RxNet
import com.felix.felixstore.rx.ext.subscribeEmpty
import com.felix.lib_arch.mvvm.BaseViewModel
import com.felix.lib_store.base.bean.AppDetailBean
import com.felix.lib_store.base.bean.AppItem
import com.felix.lib_store.base.service.ApiDelegate

class DetailViewModel : BaseViewModel() {
    var appItem = MutableLiveData<AppItem>()
    var appDetailBean = MutableLiveData<AppDetailBean>()

    fun getAppDetail(pkgName: String) {
        ApiDelegate.getAppDetail(pkgName).subscribeOn(RxNet).doOnNext {
            appDetailBean.postValue(it)
        }.subscribeEmpty()
    }
}