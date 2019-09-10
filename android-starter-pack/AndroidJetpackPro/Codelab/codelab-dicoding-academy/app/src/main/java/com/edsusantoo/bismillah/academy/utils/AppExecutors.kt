package com.edsusantoo.bismillah.academy.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


open class AppExecutors {

    private var diskIO = DiskIOThreadExecutor()
    private var networkIO = Executors.newFixedThreadPool(THREAD_COUNT)
    private var mainThread = MainThreadExecutor()

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            mainThreadHandler.post(command)
        }
    }

    companion object {

        private const val THREAD_COUNT = 3
    }

}