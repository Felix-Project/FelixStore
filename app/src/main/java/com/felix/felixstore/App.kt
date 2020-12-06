package com.felix.felixstore

import android.content.Context
import com.felix.commgmt.BaseCompApp
import com.felix.utils.AppUtils

class App : BaseCompApp() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        AppUtils.setup(this)
    }
}