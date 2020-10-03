package com.felix.lib_component_base.service

class ServiceFactory {
    companion object {
        val instance by lazy {
            ServiceFactory()
        }
    }

    lateinit var downloadService: DownloadService
}

inline val ComponentProxy
    get() = ServiceFactory.instance