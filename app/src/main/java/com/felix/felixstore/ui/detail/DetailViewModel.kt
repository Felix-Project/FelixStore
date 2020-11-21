package com.felix.felixstore.ui.detail

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.felix.felixstore.rx.ext.RxNet
import com.felix.felixstore.rx.ext.subscribeEmpty
import com.felix.lib_app_tools.AppProxy
import com.felix.lib_app_tools.toast.ToastDelegate
import com.felix.lib_arch.mvvm.BaseViewModel
import com.felix.lib_component_base.service.ComponentProxy
import com.felix.lib_component_base.service.DownloadService
import com.felix.lib_store.base.bean.AppDetailBean
import com.felix.lib_store.base.bean.AppItem
import com.felix.lib_store.base.service.ApiDelegate

class DetailViewModel : BaseViewModel() {
    var appItem = MutableLiveData<AppItem>()
    var appDetailBean = MutableLiveData<AppDetailBean>()

    //app大小
    var appSize = Transformations.map(appDetailBean) { detailBean ->
        detailBean.appSize.let { appSize ->
            Log.i(TAG, "appSize:${appSize} ")
            val ch = arrayOf('K', 'M', 'G', 'T', 'P')
            var index = -1
            var size = appSize.toFloat()
            while (size > 1024L) {
                size /= 1024L
                index++
            }
            (index.takeIf { it >= 0 }?.let {
                ch[it]
            } ?: ' ').let {
                String.format("%.2f%c", size, it)
            }
        }
    }

    //星级数
    var appStar = Transformations.map(appDetailBean) {
        Log.i(TAG, "appStar: ${it.appStartNum}")
        String.format("%.1f", it.appStartNum / 2f)
    }

    //评论数
    var appRemartNum = Transformations.map(appDetailBean) { appDetailBean ->
        Log.i(TAG, "appRemartNum:${appDetailBean.appRemarkNum} ")
        appDetailBean.appRemarkNum.takeIf { it > 10_000 }?.let {
            (it / 1000).toString() + "k"
        } ?: kotlin.run { appDetailBean.appRemarkNum.toString() }
    }

    fun getAppDetail(pkgName: String) {
        ApiDelegate.getAppDetail(pkgName).subscribeOn(RxNet).doOnNext {
            appDetailBean.postValue(it)
        }.subscribeEmpty()
    }

    fun download() {
        appDetailBean.value?.run {
            DownloadService.DownloadConfig(
                url = appDownloadUrl,
                name = " ${appPkgName}_${appVersionName}".replace('.', '_').plus(".apk")
            )
        }?.let { config ->
            ComponentProxy.downloadService.download(config, {
                install(it)
            }, {
                it.printStackTrace()
                ToastDelegate.show("下载出错")
            })
        }
    }

    private fun install(uri: Uri) {
        Intent().apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setDataAndType(uri, "application/vnd.android.package-archive")
        }.let {
            AppProxy.startActivity(it)
        }
    }
}