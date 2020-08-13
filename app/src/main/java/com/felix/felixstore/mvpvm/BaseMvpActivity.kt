package com.felix.felixstore.mvpvm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType

class BaseMvpActivity<V : IBaseView, VM : ViewModel, P : AbsBasePresenter<V, VM>> : BaseActivity(),
    IBaseView {
    protected lateinit var presenter: AbsBasePresenter<V, VM>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = newPresenter()
        presenter.attach(this, this as V)
    }

    open protected fun newPresenter(): P {
        return javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
            it as ParameterizedType
        }?.let {
            it.actualTypeArguments
        }?.let {
            it.getOrNull(3)
        }?.let {
            it as Class<P>
        }?.let {
            it.newInstance()
        }!!
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}