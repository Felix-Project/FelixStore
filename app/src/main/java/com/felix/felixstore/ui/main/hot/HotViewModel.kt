package com.felix.felixstore.ui.main.hot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.felix.felixstore.base.mvpvm.ListLiveData
import com.felix.lib_store.base.bean.AppItem

class HotViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val ch: LiveData<Char> = Transformations.map(text) {
        it.last()
    }

    val hotAppList = ListLiveData<AppItem>()
}