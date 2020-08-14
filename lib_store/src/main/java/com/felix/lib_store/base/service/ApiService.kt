package com.felix.lib_store.base.service

import com.felix.lib_store.base.bean.AppDetailBean
import com.felix.lib_store.base.bean.AppItem
import io.reactivex.rxjava3.core.Observable

interface ApiService : LocalApiSAervice, RemoteApiService {
    val remoteApiService: RemoteApiService
    override fun getAppItem(keywords: String?, page: Int): Observable<MutableList<AppItem>> {
        return remoteApiService.getAppItem(keywords, page)
    }

    override fun getCategorAppItem(categor: String?): Observable<MutableList<AppItem>> {
        return remoteApiService.getCategorAppItem(categor)
    }

    override fun getTopList(page: Int): Observable<MutableList<AppItem>> {
        return remoteApiService.getTopList(page)
    }

    override fun getCategorTest(categor: Int): Observable<String> {
        return remoteApiService.getCategorTest(categor)
    }

    override fun getAppDetail(id: String?): Observable<AppDetailBean> {
        return remoteApiService.getAppDetail(id)
    }
}