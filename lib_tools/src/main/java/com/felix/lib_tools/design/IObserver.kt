package com.felix.lib_tools.design

import com.felix.lib_tools.util.list.deleteIf
import java.lang.ref.WeakReference

interface IObserver<T> {
    val observerList: MutableList<WeakReference<(T) -> Unit>>
    fun register(observer: (T) -> Unit) {
        observerList.deleteIf { it.get() == null }
        observerList.firstOrNull { observer == it.get() }
            ?: observerList.add(WeakReference(observer))
    }

    fun unregister(observer: (T) -> Unit) {
        observerList.deleteIf {
            it.get() == observer || it.get() == null
        }
    }

    fun notify(data: T) {
        observerList.forEach {
            it.get()?.invoke(data)
        }
    }
}