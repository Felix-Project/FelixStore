package com.felix.felixstore

import com.felix.lib_app_tools.LibApp
import com.felix.lib_component_base.BaseComponentApp

class App : BaseComponentApp() {
    override fun onCreate() {
        super.onCreate()
        LibApp.install(this)
    }
}