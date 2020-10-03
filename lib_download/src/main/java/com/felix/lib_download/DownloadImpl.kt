package com.felix.lib_download

import android.net.Uri
import androidx.core.content.FileProvider
import com.felix.lib_component_base.AppProxy
import com.felix.lib_component_base.service.DownloadService
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import java.io.File

class DownloadImpl : DownloadService {
    override fun download(
        downloadConfig: DownloadService.DownloadConfig,
        onCompelete: (uri: Uri) -> Unit,
        onError: (Throwable) -> Unit,
        onProgress: ((downloaded: Int, total: Int) -> Unit)?
    ) {
        val listener = object : FileDownloadListener() {
            override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

            }

            override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                onProgress?.invoke(soFarBytes, totalBytes)
            }

            override fun completed(task: BaseDownloadTask?) {
                task?.targetFilePath?.let {
                    FileProvider.getUriForFile(AppProxy, "com.felix.lib_download.apk", File(it))
                }?.let(onCompelete) ?: kotlin.run {
                    onError.invoke(NullPointerException("file is null."))
                }
            }

            override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
            }

            override fun error(task: BaseDownloadTask?, e: Throwable?) {
                onError.invoke(e ?: NullPointerException("Throwable is null."))
            }

            override fun warn(task: BaseDownloadTask?) {
            }

        }
        FileDownloader.getImpl().create(downloadConfig.url)
            .setPath(
                File(
                    AppProxy.getExternalFilesDir("Download"),
                    downloadConfig.name
                ).absolutePath
            )
            .setListener(listener)
            .start()
    }
}