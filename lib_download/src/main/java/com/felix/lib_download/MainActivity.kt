package com.felix.lib_download

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.felix.lib_component_base.service.ComponentProxy
import com.felix.lib_component_base.service.DownloadService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = this::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvTest.setOnClickListener {
            DownloadService.DownloadConfig(
                url =
                "http://app.mi.com/download/441492?id=com.mmbox.xbrowser.pro&ref=search&nonce=3927312507183524326%3A26695128&appClientId=2882303761517485445&appSignature=ZEtfEcLTcRxmQ3VPQ34bWv69F_4JqmLbFEveS1U6FKk",
                name = "com_mmbox_xbrowser_pro.apk"
            ).let { config ->
                ComponentProxy.downloadService.download(config, {
                    Log.i(TAG, "onCreate: ${it.path}")
                }, {
                    it.printStackTrace()
                }, { downloaded, total ->
                    Log.d(TAG, "onCreate: $downloaded/$total")
                })
            }
        }
    }
}