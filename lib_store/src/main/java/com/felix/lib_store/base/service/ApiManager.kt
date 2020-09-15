package com.felix.lib_store.base.service

import com.felix.lib_store.base.conveter.HtmlFactory
import com.felix.lib_store.base.util.AppUrl
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

internal class ApiManager : ApiService {
    companion object {
        val instance by lazy { ApiManager() }
    }

    override val remoteApiService: RemoteApiService
        get() = HttpLoggingInterceptor.Level.BODY.let {
            HttpLoggingInterceptor().setLevel(it)
        }.let {
            OkHttpClient.Builder().addNetworkInterceptor(it)
        }.build().let {
            Retrofit.Builder().client(it)
        }.run {
            baseUrl(AppUrl)
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            addConverterFactory(HtmlFactory())
            build()
        }.create(RemoteApiService::class.java)
}

val ApiDelegate: ApiService
    get() = ApiManager.instance