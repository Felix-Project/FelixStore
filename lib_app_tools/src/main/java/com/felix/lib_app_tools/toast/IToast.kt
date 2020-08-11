package com.felix.lib_app_tools.toast

import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes

interface IToast {
    fun show(msg: String?, duration: Int = Toast.LENGTH_SHORT, gravity: Int = Gravity.CENTER)

    fun show(
        @StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT,
        gravity: Int = Gravity.CENTER
    )
}