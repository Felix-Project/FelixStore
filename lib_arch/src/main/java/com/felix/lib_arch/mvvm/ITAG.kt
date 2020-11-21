package com.felix.lib_arch.mvvm

interface ITAG {
    val TAG: String
        get() = this::class.java.simpleName
}