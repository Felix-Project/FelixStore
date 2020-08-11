package com.sf.warehouse.lib_basic.thread

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

val <T> T.DBThreadProxy
    get() = ThreadPoolManager.instance.getDBThreadPool()

val <T> T.NetThreadProxy
    get() = ThreadPoolManager.instance.getNetThreadPool()

val <T> T.ComputeThreadProxy
    get() = ThreadPoolManager.instance.getComputeThreadPool()

val <T> T.ScheduleThreadProxy
    get() = ThreadPoolManager.instance.getScheduleThreadPool()

val <T> T.BackstageThreadProxy
    get() = ThreadPoolManager.instance.getBackstageThreadPool()

val <T> T.ThreadProxy
    get() = ThreadPoolManager.instance.getCommonThreadPool()