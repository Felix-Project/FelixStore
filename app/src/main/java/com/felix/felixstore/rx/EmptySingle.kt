package com.felix.felixstore.rx

import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: EmptySingle
 */
class EmptySingle<T> : SingleObserver<T> {
    override fun onSuccess(t: T) {

    }

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onError(e: Throwable?) {
        e?.printStackTrace()
    }
}