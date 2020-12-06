package com.felix.arch.mvvm

import android.os.Bundle

open class BaseMvvmFragment<VM : BaseViewModel> : BaseFragment(), BaseMvvmView<VM> {

    override lateinit var viewModel: VM
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }
}