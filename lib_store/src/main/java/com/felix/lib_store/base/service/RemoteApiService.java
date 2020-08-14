package com.felix.lib_store.base.service;

import com.felix.lib_store.base.bean.AppDetailBean;
import com.felix.lib_store.base.bean.AppItem;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteApiService {
    @GET("searchAll?typeall=phone")
    Observable<List<AppItem>> getAppItem(@Query("keywords") String keywords, @Query("page") int page);

    @GET("hotCatApp/{categor}")
    Observable<List<AppItem>> getCategorAppItem(@Path("categor") String categor);

    @GET("topList")
    Observable<List<AppItem>> getTopList(@Query("page") int page);


    @GET("hotCatApp/{categor}")
    Observable<String> getCategorTest(@Path("categor") int categor);

    @GET("details")
    Observable<AppDetailBean> getAppDetail(@Query("id") String id);
}
