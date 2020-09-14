package com.felix.lib_gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class GsonManager {
    companion object {
        val instance by lazy { GsonManager() }
    }

    val gson: Gson = Gson()
}

val GsonProxy
    get() = GsonManager.instance.gson

inline fun <T> T.toJson(): String = GsonProxy.toJson(this) ?: ""
inline fun <reified T> String.fromJson(): T? = GsonProxy.fromJson<T>(this, object :
    TypeToken<T>() {}.type)