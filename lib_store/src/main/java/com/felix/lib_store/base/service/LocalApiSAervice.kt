package com.felix.lib_store.base.service

import android.util.Log
import com.felix.lib_gson.fromJson
import com.felix.lib_gson.toJson
import com.felix.lib_store.base.bean.AppCategory
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

interface LocalApiSAervice {
    val str: String
        get() = "[{\"id\":\"1\",\"name\":\"金融理财\"},{\"id\":\"2\",\"name\":\"聊天社交\"},{\"id\":\"3\",\"name\":\"旅行交通\"},{\"id\":\"4\",\"name\":\"居家生活\"},{\"id\":\"5\",\"name\":\"实用工具\"},{\"id\":\"6\",\"name\":\"摄影摄像\"},{\"id\":\"7\",\"name\":\"图书阅读\"},{\"id\":\"8\",\"name\":\"体育运动\"},{\"id\":\"9\",\"name\":\"时尚购物\"},{\"id\":\"10\",\"name\":\"效率办公\"},{\"id\":\"11\",\"name\":\"新闻资讯\"},{\"id\":\"12\",\"name\":\"学习教育\"},{\"id\":\"13\",\"name\":\"娱乐消遣\"},{\"id\":\"14\",\"name\":\"医疗健康\"},{\"id\":\"15\",\"name\":\"游戏\"},{\"id\":\"16\",\"name\":\"战争策略\"},{\"id\":\"17\",\"name\":\"动作枪战\"},{\"id\":\"18\",\"name\":\"赛车体育\"},{\"id\":\"19\",\"name\":\"网游RPG\"},{\"id\":\"20\",\"name\":\"棋牌桌游\"},{\"id\":\"21\",\"name\":\"格斗快打\"},{\"id\":\"22\",\"name\":\"儿童益智\"},{\"id\":\"23\",\"name\":\"休闲创意\"},{\"id\":\"25\",\"name\":\"飞行空战\"},{\"id\":\"26\",\"name\":\"跑酷闯关\"},{\"id\":\"27\",\"name\":\"影音视听\"},{\"id\":\"28\",\"name\":\"塔防迷宫\"},{\"id\":\"29\",\"name\":\"模拟经营\"}]"

    fun getCategorList(): Observable<List<AppCategory>> =
        str.fromJson(object : TypeToken<List<AppCategory>>() {}.type)


    @Deprecated("测试使用，不要调用")
    fun update() {
        Observable.range(0, 30).map {
            AppCategory().apply {
                name = ApiDelegate.getCategorTest(it).runCatching { blockingFirst() }.getOrNull() ?: ""
                id = it.toString()
            }
        }.filter { it.name.isNotEmpty() }.doOnNext {
            Log.i("hmf", it.toJson())
        }.toList().toObservable().doOnNext {
            Log.i("hmf", it.toJson())
        }.subscribeOn(Schedulers.io()).subscribe()
    }

}