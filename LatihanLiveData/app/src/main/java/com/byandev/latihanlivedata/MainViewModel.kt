package com.byandev.latihanlivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    companion object {
        private const val ONE_SECOND = 1000
    }

    private val initialTimer: Long = SystemClock.elapsedRealtime()
    private val elapsedTime = MutableLiveData<Long?>()

    /*
    MutableLiveData = bisa kita ubah valuenya
    LiveData = read only
     */

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object  : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - initialTimer) / 1000
                elapsedTime.postValue(newValue)
            }

        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    fun getElapsedTime() : LiveData<Long?> {
        return elapsedTime
    }
}