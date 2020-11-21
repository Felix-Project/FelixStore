package com.felix.lib_component_base.service

import android.net.Uri

interface DownloadService {
    data class DownloadConfig(var url: String, var name: String)

    fun download(
        downloadConfig: DownloadConfig,
        onCompelete: (uri: Uri) -> Unit,
        onError: (Throwable) -> Unit,
        onProgress: ((downloaded: Int, total: Int) -> Unit)? = null
    )
}