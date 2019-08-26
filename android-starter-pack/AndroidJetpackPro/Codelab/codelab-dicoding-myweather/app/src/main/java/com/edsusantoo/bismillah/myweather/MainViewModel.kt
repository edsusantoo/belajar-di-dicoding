package com.edsusantoo.bismillah.myweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {
    private val API_KEY: String = "cc2abbd88025b40954a307538886869d"
    private val listWeathers: MutableLiveData<ArrayList<WeatherItems>> = MutableLiveData()


    fun setWeather(cities: String) {
        val client = AsyncHttpClient()
        val listItems = ArrayList<WeatherItems>()
        val url = "https://api.openweathermap.org/data/2.5/group?id=$cities&units=metric&appid=$API_KEY"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                val responseObject = JSONObject(result)
                val list = responseObject.getJSONArray("list")
                for (i in 0 until list.length()) {
                    val weather = list.getJSONObject(i)
                    val weatherItems = WeatherItems(weather)
                    listItems.add(weatherItems)
                }

                listWeathers.postValue(listItems)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("onFailure : ", error?.message)
            }

        })

    }

    fun getWeathers(): LiveData<ArrayList<WeatherItems>> {
        return listWeathers
    }
}