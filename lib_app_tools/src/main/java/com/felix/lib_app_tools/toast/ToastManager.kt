package com.felix.lib_app_tools.toast

import android.view.Gravity
import android.widget.Toast
import com.felix.lib_app_tools.AppProxy
import com.felix.lib_tools.handler.UIProxy

internal class ToastManager : IToast {
    companion object {
        val instance: ToastManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ToastManager()
        }
    }

    private lateinit var toast: Toast

    init {
        UIProxy.post {
            toast = Toast.makeText(AppProxy, "", Toast.LENGTH_SHORT)
        }
    }

    override fun show(msg: String?, duration: Int, gravity: Int) {
        msg?.takeIf { it.isNotEmpty() }?.let {
            UIProxy.post {
                toast.setText(msg)
                toast.duration = duration
                toast.setGravity(gravity, 0, 0)
                toast.show()
            }
        }
    }

    override fun show(resId: Int, duration: Int, gravity: Int) {
        resId?.takeIf { it > 0 }?.let {
            UIProxy.post {
                toast.setText(resId)
                toast.duration = duration
                toast.setGravity(gravity, 0, 0)
                toast.show()
            }
        }
    }
}

val <T> T.ToastDelegate: IToast
    get() = ToastManager.instance

fun String.showToast(duration: Int = Toast.LENGTH_SHORT, gravity: Int = Gravity.CENTER) {
    ToastDelegate.show(this, duration, gravity)
}