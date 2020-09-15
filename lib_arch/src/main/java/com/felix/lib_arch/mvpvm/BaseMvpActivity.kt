package com.felix.lib_arch.mvpvm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType

open class BaseMvpActivity<V : IBaseView,
        VM : ViewModel,
        P : AbsBasePresenter<V, VM>,
        VD : AbsBaseViewDelegate<P, VM>> : BaseActivity(), IBaseView {
    protected lateinit var presenter: P
    protected lateinit var viewDelegate: VD
    protected lateinit var iView:V
    protected val viewModel: VM
        get() = presenter.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDelegate = newViewDelegate()
        iView=viewDelegate as V
        presenter = newPresenter()
        presenter.attach(this, iView)
        viewDelegate.attach(presenter, this, null, delegate)
        viewDelegate.onActivityCreated()
    }

    open protected fun newPresenter(): P {
        return javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.actualTypeArguments?.getOrNull(2)?.let {
            it as Class<P>
        }?.newInstance()!!
    }

    open protected fun newViewDelegate(): VD {
        return javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.actualTypeArguments?.getOrNull(3)?.let {
            it as Class<VD>
        }?.newInstance()!!
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
        viewDelegate.onDestroy()
    }
}