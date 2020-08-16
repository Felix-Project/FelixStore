package com.felix.felixstore.ui.main.hot

import android.os.Bundle
import com.felix.lib_arch.mvpvm.BaseMvpFragment

class HotFragment : BaseMvpFragment<HotView, HotViewModel, HotPresenterImpl, HotViewDelegate>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.getHotAppList()
    }
}