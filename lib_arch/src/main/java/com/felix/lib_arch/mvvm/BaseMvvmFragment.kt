package com.felix.lib_arch.mvvm

import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.felix.lib_arch.mvpvm.BaseFragment
import java.lang.reflect.ParameterizedType

class BaseMvvmFragment<VM : BaseViewModel> : BaseFragment() {
    lateinit var viewModel: VM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.let {
            it.actualTypeArguments
        }?.let {
            it.getOrNull(0)
        }?.let {
            it as Class<VM>
        }?.let {
            this.viewModel = ViewModelProvider(this).get(it)
        } ?: kotlin.run {
            throw  NullPointerException("no ViewModel find.")
        }
        lifecycle.addObserver(viewModel as LifecycleObserver)
    }
}