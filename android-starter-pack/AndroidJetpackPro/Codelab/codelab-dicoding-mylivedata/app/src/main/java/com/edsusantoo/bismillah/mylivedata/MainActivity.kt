package com.edsusantoo.bismillah.mylivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mLiveDataTimeViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLiveDataTimeViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        subscribe()

    }

    private fun subscribe() {
        val elapsedTimeObserver: Observer<Long> = Observer {
            val newText = this.resources.getString(R.string.seconds, it)
            timer_textview.text = newText
        }

        mLiveDataTimeViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}
