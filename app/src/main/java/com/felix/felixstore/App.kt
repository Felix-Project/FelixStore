package com.felix.felixstore

import android.app.Application
import com.felix.lib_app_tools.LibApp

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LibApp.install(this)
    }
}