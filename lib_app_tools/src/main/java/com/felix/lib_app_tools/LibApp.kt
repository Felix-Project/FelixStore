package com.felix.lib_app_tools

import android.app.Application

object LibApp {
    var app: Application? = null
    fun install(application: Application) {
        app = application
    }
}

val AppProxy: Application
    get() = LibApp.app!!