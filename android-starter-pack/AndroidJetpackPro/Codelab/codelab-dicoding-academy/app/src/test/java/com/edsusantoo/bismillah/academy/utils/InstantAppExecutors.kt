package com.edsusantoo.bismillah.academy.utils

import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors() {
    private val instant = Executor { it.run() }

    init {
        instant
    }
}