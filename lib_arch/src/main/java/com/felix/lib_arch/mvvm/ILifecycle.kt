package com.felix.arch.mvvm

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

interface ILifecycle : ITAG {
    fun <T> observe(data: LiveData<T>, action: (T) -> Unit) {
        this.takeIf { it is LifecycleOwner }?.let {
            it as LifecycleOwner
        }?.let { owner ->
            data.observe(owner, Observer { data ->
                action.invoke(data)
            })
        } ?: kotlin.run {
            val msg = "${this::class.java.name} should impelement LifecycleOwner"
            Log.e(TAG, "error in ILifecycle", NullPointerException(msg))
        }
    }
}