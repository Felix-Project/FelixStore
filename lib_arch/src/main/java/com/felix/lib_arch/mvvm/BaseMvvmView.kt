package com.felix.lib_arch.mvvm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import java.lang.reflect.ParameterizedType

interface BaseMvvmView<VM : BaseViewModel> : LifecycleOwner, ILifecycle {
    var viewModel: VM

    fun initViewModel() {
        javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.actualTypeArguments?.getOrNull(0)?.let {
            it as Class<VM>
        }?.let {
            this.viewModel = ViewModelProvider(this as ViewModelStoreOwner).get(it)
        } ?: kotlin.run {
            throw  NullPointerException("no ViewModel find.")
        }
        lifecycle.addObserver(viewModel as LifecycleObserver)
    }
}