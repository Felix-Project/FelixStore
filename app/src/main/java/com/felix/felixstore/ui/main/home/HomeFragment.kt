package com.felix.felixstore.ui.main.home

import android.os.Bundle
import com.felix.felixstore.mvpvm.BaseMvpFragment

class HomeFragment : BaseMvpFragment<HomeView, HomeViewModel, HomePresenterImpl, HomeViewDelegate>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}