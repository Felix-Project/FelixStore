package com.felix.arch.mvvm

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(),
    IloadDialog,
    ITAG {
    override var ctx: Context? = null
        get() = this
    override var dialog: ProgressDialog? = null
    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}