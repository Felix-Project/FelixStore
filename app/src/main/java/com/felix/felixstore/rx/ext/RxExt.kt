package com.felix.felixstore.rx.ext

import com.felix.felixstore.rx.EmptyObserve
import com.felix.felixstore.rx.EmptySingle
import com.felix.lib_tools.thread.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: RxExt
 */
//Schedulers
inline val RxNet: Scheduler
    get() = Schedulers.from(NetThreadDelegate)

inline val RxMain: Scheduler
    get() = AndroidSchedulers.mainThread()

inline val RxCompute: Scheduler
    get() = Schedulers.from(ComputeThreadDelegate)

inline val RxSchedule: Scheduler
    get() = Schedulers.from(ScheduleThreadDelegate)

inline val RxDb: Scheduler
    get() = Schedulers.from(DBThreadDelegate)

inline val RxBackstage: Scheduler
    get() = Schedulers.from(BackstageThreadDelegate)

//subscribe
fun <T> Observable<T>.subscribeEmpty() {
    this.subscribe(EmptyObserve())
}

fun <T> Single<T>.subscribeEmpty() {
    this.subscribe(EmptySingle())
}