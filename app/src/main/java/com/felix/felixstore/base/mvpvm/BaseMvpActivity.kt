package com.felix.felixstore.base.mvpvm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType

class BaseMvpActivity<V : IBaseView,
        VM : ViewModel,
        P : AbsBasePresenter<V, VM>,
        VD : AbsBaseViewDelegate<P, VM>> : BaseActivity(), IBaseView {
    protected lateinit var presenter: P
    protected lateinit var viewDelegate: VD
    protected val viewModel: VM
        get() = presenter.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = newPresenter()
        presenter.attach(this, this as V)
        viewDelegate = newViewDelegate()
        viewDelegate.attach(presenter, this, null, delegate)
        viewDelegate.onActivityCreated()
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

    open protected fun newViewDelegate(): VD {
        return javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.let {
            it.actualTypeArguments
        }?.let {
            it.getOrNull(3)
        }?.let {
            it as Class<VD>
        }?.let {
            it.newInstance()
        }!!
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}