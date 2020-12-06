package com.felix.arch.mvvm

interface ITAG {
    val TAG: String
        get() = this::class.java.simpleName
}