package com.edsusantoo.bismillah.myweather

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: WeatherAdapter
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getWeathers().observe(this, getWeather)


        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        btnCity.setOnClickListener {
            val city: String = editCity.text.toString()

            if (TextUtils.isEmpty(city)) return@setOnClickListener

            mainViewModel.setWeather(city)

            showLoading(true)
        }

    }

    private val getWeather: Observer<ArrayList<WeatherItems>> = Observer {
        if (it != null) {
            adapter.setData(it)
            showLoading(false)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
