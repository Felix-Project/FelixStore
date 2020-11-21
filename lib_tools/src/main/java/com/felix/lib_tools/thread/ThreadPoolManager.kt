package com.felix.lib_tools.thread

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @Author: Mingfa.Huang
 * @Date: 2019/12/2
 * @Des: ThreadPoolManager
 */
internal class ThreadPoolManager {
    companion object {
        private var CORE_SIZE = Runtime.getRuntime().availableProcessors() + 1
        val instance: ThreadPoolManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ThreadPoolManager()
        }
    }

    /**
     * 数据库操作的线程池
     */
    fun getDBThreadPool(): ExecutorService {
        return Executors.newSingleThreadExecutor(ThreadCreator("db", ThreadCreator.PRIORITY_DB))
    }

    /**
     * 网络io操作的线程池
     */
    fun getNetThreadPool(): ExecutorService {
        return Executors.newFixedThreadPool(
            CORE_SIZE,
            ThreadCreator("net", ThreadCreator.PRIORITY_NET)
        )
    }

    /**
     * 计算操作的线程池
     */
    fun getComputeThreadPool(): ExecutorService {
        return Executors.newCachedThreadPool(
            ThreadCreator(
                "compute",
                ThreadCreator.PRIORITY_COMPUTE
            )
        )
    }

    /**
     * 定时任务线程池
     */
    fun getScheduleThreadPool(): ExecutorService {
        return Executors.newScheduledThreadPool(
            CORE_SIZE,
            ThreadCreator("schedule", ThreadCreator.PRIORITY_SCHEDULE)
        )
    }

    /**
     * 后台操作线程池
     */
    fun getBackstageThreadPool(): ExecutorService {
        return Executors.newFixedThreadPool(
            CORE_SIZE,
            ThreadCreator("backstage", ThreadCreator.PRIORITY_BG)
        )
    }

    /**
     * 普通线程池，尽量用其他几个代替，无法分类的放在这里
     */
    fun getCommonThreadPool(): ExecutorService {
        return Executors.newFixedThreadPool(
            CORE_SIZE,
            ThreadCreator("common", ThreadCreator.PRIORITY_COMMON)
        )
    }
}

val DBThreadDelegate
    get() = ThreadPoolManager.instance.getDBThreadPool()

val NetThreadDelegate
    get() = ThreadPoolManager.instance.getNetThreadPool()

val ComputeThreadDelegate
    get() = ThreadPoolManager.instance.getComputeThreadPool()

val ScheduleThreadDelegate
    get() = ThreadPoolManager.instance.getScheduleThreadPool()

val BackstageThreadDelegate
    get() = ThreadPoolManager.instance.getBackstageThreadPool()

val ThreadDelegate
    get() = ThreadPoolManager.instance.getCommonThreadPool()