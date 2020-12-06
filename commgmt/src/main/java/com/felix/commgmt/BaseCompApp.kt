package com.felix.commgmt

import android.app.Application
import com.felix.combase.IAppInit
import com.felix.downloader.impl.DownloadAppInit

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/12/5
 * @Des: BaseCompApp
 */
open class BaseCompApp : Application(), IAppInit {
    val appModule: Array<IAppInit> = arrayOf(DownloadAppInit())

    override fun onCreate() {
        super.onCreate()
        initModule(this)
    }

    override fun initModule(app: Application) {
        for (iAppInit in appModule) {
            iAppInit.initModule(app)
        }
    }
}