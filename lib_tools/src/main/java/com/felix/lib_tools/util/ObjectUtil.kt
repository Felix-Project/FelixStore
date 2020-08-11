package com.felix.lib_tools.util

import android.os.Parcel
import android.os.Parcelable

fun <T : Parcelable> Parcelable.copy(): T? {
    var parcel: Parcel? = null
    try {
        return Parcel.obtain()
            .also { parcel = it }
            .also {
                it.writeParcelable(this, 0)
                it.setDataPosition(0)
            }
            .let {
                it.readParcelable(this::class.java.classLoader)
            }
    } finally {
        parcel?.recycle();
    }
}