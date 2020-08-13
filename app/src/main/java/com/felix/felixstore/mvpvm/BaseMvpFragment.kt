package com.felix.felixstore.mvpvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType

open class BaseMvpFragment<V : IBaseView,
        VM : ViewModel,
        P : AbsBasePresenter<V, VM>,
        VH : AbsBaseViewDelegate<VM>> :
    BaseFragment(),
    IBaseView {
    protected lateinit var presenter: P
    protected lateinit var viewDelegate: VH
    protected val ViewModel: VM
        get() = presenter.viewModel

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDelegate = newViewHelper()
        return viewDelegate.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = newPresenter()
        presenter.attach(this, this as V)
        viewDelegate.attach(presenter.viewModel, this, view)
        viewDelegate.onActivityCreated(savedInstanceState)
    }

    open protected fun newViewHelper(): VH {
        return javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.let {
            it.actualTypeArguments
        }?.let {
            it.getOrNull(3)
        }?.let {
            it as Class<VH>
        }?.let {
            it.newInstance()
        }!!
    }

    open protected fun newPresenter(): P {
        return javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.let {
            it.actualTypeArguments
        }?.let {
            it.getOrNull(2)
        }?.let {
            it as Class<P>
        }?.let {
            it.newInstance()
        }!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    protected fun <T> observe(data: LiveData<T>, action: (T) -> Unit) {
        data.observe(this as LifecycleOwner, Observer {
            action.invoke(it)
        })
    }

}