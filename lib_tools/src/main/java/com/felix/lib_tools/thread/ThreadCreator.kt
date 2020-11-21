package com.felix.lib_tools.thread

import android.os.Process
import java.util.concurrent.ThreadFactory

/**
 * @Author: Mingfa.Huang
 * @Date: 2019/12/2
 * @Des: ThreadCreator
 */
internal class ThreadCreator(var name: String, var priority: Int = Thread.NORM_PRIORITY) :
    ThreadFactory {
    companion object {
        var state: Int = 0

        const val PRIORITY_MAX = -20
        const val PRIORITY_MIN = 19

        const val PRIORITY_COMPUTE = Process.THREAD_PRIORITY_FOREGROUND + 1
        const val PRIORITY_NET = Process.THREAD_PRIORITY_DEFAULT
        const val PRIORITY_DB = Process.THREAD_PRIORITY_FOREGROUND
        const val PRIORITY_BG = Process.THREAD_PRIORITY_BACKGROUND
        const val PRIORITY_SCHEDULE = Process.THREAD_PRIORITY_DEFAULT

        const val PRIORITY_COMMON = Process.THREAD_PRIORITY_DEFAULT
    }

    override fun newThread(r: Runnable?): Thread {
        val t = Thread(Thread.currentThread().threadGroup, r, "$name - Thread - ${state++}")
//        Process.setThreadPriority(t.id.toInt(), priority)
        if (t.isDaemon) {
            t.isDaemon = false
        }
//        if (t.priority != priority) {
//            t.priority = priority
//        }
        return t
    }
}