package com.felix.felixstore.ui.main.hot

import android.os.Bundle
import com.felix.felixstore.base.mvpvm.BaseMvpFragment

class HotFragment : BaseMvpFragment<HotView, HotViewModel, HotPresenterImpl, HotViewDelegate>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.getHotAppList()
    }
}