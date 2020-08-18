package com.felix.felixstore.ui.detail

import com.felix.lib_arch.mvpvm.IBasePresenter
import com.felix.lib_arch.mvpvm.IBaseView

interface DetailView : IBaseView {
}

interface DetailPresenter : IBasePresenter {
    fun getAppDetail(pkgName:String)
}