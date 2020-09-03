package com.felix.lib_arch.mvpvm

interface ITAG {
    val TAG:String
    get() = this::class.java.simpleName
}