package com.felix.lib_arch.mvvm

import android.os.Bundle

open class BaseMvvmActivity<VM : BaseViewModel> : BaseActivity(), BaseMvvmView<VM> {
    override lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }
}