package com.felix.felixstore.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felix.lib_store.base.bean.AppDetailBean
import com.felix.lib_store.base.bean.AppItem

class DetailViewModel : ViewModel() {
    var appItem = MutableLiveData<AppItem>()
    var appDetailBean = MutableLiveData<AppDetailBean>()
}