package com.edsusantoo.bismillah.myworkmanager.network;

import com.edsusantoo.bismillah.myworkmanager.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String apikey);
}
