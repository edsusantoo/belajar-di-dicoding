package com.edsusantoo.bismillah.myworkmanager.model;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("deg")
    private int deg;

    @SerializedName("speed")
    private double speed;

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return
                "Wind{" +
                        "deg = '" + deg + '\'' +
                        ",speed = '" + speed + '\'' +
                        "}";
    }
}