package com.felix.lib_arch.mvpvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import java.lang.NullPointerException
import java.lang.reflect.ParameterizedType

/**
 * 建议无参构造函数
 */
abstract class AbsBasePresenter<V : IBaseView, VM : ViewModel>:ITAG {
    lateinit var viewModel: VM
    protected lateinit var view: V
    open fun attach(viewModelStoreOwner: ViewModelStoreOwner, view: V) {
        this.view = view
        javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.let {
            it.actualTypeArguments
        }?.let {
            it.getOrNull(1)
        }?.let {
            it as Class<VM>
        }?.let {
            this.viewModel = ViewModelProvider(viewModelStoreOwner).get(it)
        } ?: kotlin.run {
            throw  NullPointerException("no ViewModel find.")
        }
    }

    open fun detach() {

    }
}