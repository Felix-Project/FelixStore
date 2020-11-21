package com.felix.lib_component_base

import android.app.Application
import com.felix.lib_component_base.app.appModule

open class BaseComponentApp : Application(), IAppInit {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initModule(this)
    }

    override fun initModule(app: Application) {
        appModule.forEach {
            Class.forName(it).runCatching {
                this.newInstance()
            }.getOrNull()?.takeIf {
                it is IAppInit
            }?.let {
                it as IAppInit
            }?.let {
                it.initModule(app)
            }
        }
    }
}

inline val AppProxy
    get() = BaseComponentApp.instance