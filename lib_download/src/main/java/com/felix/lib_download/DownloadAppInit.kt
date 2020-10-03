package com.felix.lib_download

import android.app.Application
import com.felix.lib_component_base.IAppInit
import com.felix.lib_component_base.service.ComponentProxy
import com.liulishuo.filedownloader.FileDownloader

class DownloadAppInit : IAppInit {
    override fun initModule(app: Application) {
        FileDownloader.setup(app)
        ComponentProxy.downloadService = DownloadImpl()
    }
}