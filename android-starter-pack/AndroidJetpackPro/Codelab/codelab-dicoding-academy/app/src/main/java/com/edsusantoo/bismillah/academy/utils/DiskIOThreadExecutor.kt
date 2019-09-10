package com.edsusantoo.bismillah.academy.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskIOThreadExecutor : Executor {

    private val mDiskIO = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable?) {
        mDiskIO.execute(command)
    }

}