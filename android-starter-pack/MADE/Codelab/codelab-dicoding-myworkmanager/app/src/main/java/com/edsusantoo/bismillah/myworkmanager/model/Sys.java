package com.edsusantoo.bismillah.myworkmanager.model;

import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private int sunrise;

    @SerializedName("sunset")
    private int sunset;

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private int type;

    @SerializedName("message")
    private double message;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return
                "Sys{" +
                        "country = '" + country + '\'' +
                        ",sunrise = '" + sunrise + '\'' +
                        ",sunset = '" + sunset + '\'' +
                        ",id = '" + id + '\'' +
                        ",type = '" + type + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}