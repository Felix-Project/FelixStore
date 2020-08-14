package com.felix.felixstore.rx

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: EmptyObserve
 */
class EmptyObserve<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable?) {
        e?.printStackTrace()
    }
}