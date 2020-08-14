package com.felix.felixstore.rx.ext

import com.felix.felixstore.rx.EmptyObserve
import com.felix.felixstore.rx.EmptySingle
import com.sf.warehouse.lib_basic.thread.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: RxExt
 */
//Schedulers
val <T> T.RxNet
    get() = Schedulers.from(NetThreadDelegate)

val <T> T.RxMain
    get() = AndroidSchedulers.mainThread()

val <T> T.RxCompute
    get() = Schedulers.from(ComputeThreadDelegate)

val <T> T.RxSchedule
    get() = Schedulers.from(ScheduleThreadDelegate)

val <T> T.RxDb
    get() = Schedulers.from(DBThreadDelegate)

val <T> T.RxBackstage
    get() = Schedulers.from(BackstageThreadDelegate)

//subscribe
fun <T> Observable<T>.subscribeEmpty() {
    this.subscribe(EmptyObserve())
}

fun <T> Single<T>.subscribeEmpty() {
    this.subscribe(EmptySingle())
}