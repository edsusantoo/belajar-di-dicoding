package com.edsusantoo.bismillah.myweather

import org.json.JSONObject
import java.text.DecimalFormat

data class WeatherItems(
        private val objects: JSONObject
) {
    var id: Int? = 0
    var name: String? = null
    var currentWeather: String? = null
    var description: String? = null
    var temperature: String? = null

    init {
        id = objects.getInt("id")
        name = objects.getString("name")
        currentWeather = objects.getJSONArray("weather").getJSONObject(0).getString("main")
        description = objects.getJSONArray("weather").getJSONObject(0).getString("description")
        val tempInKelvin = objects.getJSONObject("main").getDouble("temp")
        val tempInCelsius = tempInKelvin - 273
        temperature = DecimalFormat("##.##").format(tempInCelsius)
    }


}