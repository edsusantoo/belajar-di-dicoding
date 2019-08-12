package com.edsusantoo.bismillah.myworkmanager.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static Retrofit getRetrofit() {

        Gson gsonBuilder = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();
    }

    public static ApiServices getApiServices() {

        return getRetrofit().create(ApiServices.class);
    }


}
