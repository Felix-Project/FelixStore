package com.felix.felixstore.ui.main.search

import androidx.lifecycle.ViewModel
import com.felix.lib_arch.mvpvm.ListLiveData
import com.felix.lib_store.base.bean.AppItem

class SearchViewModel : ViewModel() {
    val hotAppList = ListLiveData<AppItem>()
}