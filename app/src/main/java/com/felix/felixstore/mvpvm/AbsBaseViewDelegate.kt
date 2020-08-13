package com.felix.felixstore.mvpvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.android.extensions.LayoutContainer

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: AbsBaseViewHelper
 */
abstract class AbsBaseViewDelegate<VM : ViewModel> : LayoutContainer {
    override var containerView: View? = null
    private var delegate: AppCompatDelegate? = null

    //fragment的时候需要重写，直接返回view，不建议做其他处理
    open fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return null
    }

    /**
     *fragment 做view的初始化操作，activity调用setContentView后做初始化操作
     */
    open fun onActivityCreated(savedInstanceState: Bundle?) {
    }

    protected fun setContentView(@LayoutRes resId: Int) {
        delegate?.setContentView(resId)
        delegate?.let {
            containerView = it.findViewById(android.R.id.content)
        }
    }

    protected fun setContentView(view: View?) {
        delegate?.setContentView(view)
        delegate?.let {
            containerView = it.findViewById(android.R.id.content)
        }
    }

    protected fun setContentView(view: View?, lp: ViewGroup.LayoutParams?) {
        delegate?.setContentView(view, lp)
        delegate?.let {
            containerView = it.findViewById(android.R.id.content)
        }
    }

    protected lateinit var viewModel: VM
    private lateinit var lifecycleOwner: LifecycleOwner

    /**
     * @param view fragment传递，activity由于还没初始化，后续初始化
     * @param delegate activity的时候传递，fragment默认置空即可
     */
    fun attach(
        viewModel: VM,
        lifecycleOwner: LifecycleOwner,
        view: View?,
        delegate: AppCompatDelegate? = null
    ) {
        this.lifecycleOwner = lifecycleOwner
        this.viewModel = viewModel
        this.containerView = view
        this.delegate = delegate
    }

    protected fun <T> observe(data: LiveData<T>, action: (T) -> Unit) {
        data.observe(lifecycleOwner, Observer {
            action.invoke(it)
        })
    }
}