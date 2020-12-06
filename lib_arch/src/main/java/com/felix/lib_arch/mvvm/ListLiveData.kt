package com.felix.arch.mvvm

import androidx.lifecycle.LiveData

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/13
 * @Des: ListLiveData
 */
class ListLiveData<T> : LiveData<MutableList<T>>() {
    private val data = mutableListOf<T>()

    fun addValue(value: T) {
        data.add(value)
        super.postValue(data)
    }

    fun addValue(value: List<T>) {
        data.addAll(value)
        super.postValue(data)
    }
}
