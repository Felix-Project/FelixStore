package com.felix.felixstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.felix.lib_gson.toJson
import com.felix.lib_store.base.service.ApiProxy
import com.felix.lib_store.base.bean.AppCategory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ApiProxy.getAppItem("哔哩哔哩", 1).subscribeOn(Schedulers.io()).doOnError {
//            it.printStackTrace()
//        }.doOnNext {
//            Log.i("hmf", it.toJson())
//        }.map {
//            it.first()
//        }.flatMap {
//            ApiProxy.getHotAppItem(it.appCategoryId)
//        }.doOnNext {
//            Log.i("hmf", it.toJson())
//        }.subscribe()
        test()
        tvTest.setOnClickListener { test() }
    }

    fun test() {

        ApiProxy.getAppDetail("com.tencent.mtt").subscribeOn(Schedulers.io()).doOnNext {
            Log.i("hmf", it.toJson())
        }.subscribe()
    }
}