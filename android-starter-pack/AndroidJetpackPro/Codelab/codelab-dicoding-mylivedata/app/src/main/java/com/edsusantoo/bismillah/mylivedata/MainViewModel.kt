package com.edsusantoo.bismillah.mylivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {
    private val oneSecond: Long = 1000
    private var mInitialTime: Long = 0

    private val mElapsedTime: MutableLiveData<Long> = MutableLiveData()

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        val time = Timer()

        time.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, oneSecond, oneSecond)
    }

    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }
}