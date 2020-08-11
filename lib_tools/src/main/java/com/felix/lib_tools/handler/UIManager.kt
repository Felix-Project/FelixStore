package com.felix.lib_tools.handler

import android.os.Handler
import android.os.Looper


val <T> T.UIProxy by lazy {
    Handler(Looper.getMainLooper())
}