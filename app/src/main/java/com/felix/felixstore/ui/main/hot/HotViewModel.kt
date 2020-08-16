package com.felix.felixstore.ui.main.hot

import androidx.lifecycle.ViewModel
import com.felix.lib_arch.mvpvm.ListLiveData
import com.felix.lib_store.base.bean.AppItem

class HotViewModel : ViewModel() {
    val hotAppList = ListLiveData<AppItem>()
}