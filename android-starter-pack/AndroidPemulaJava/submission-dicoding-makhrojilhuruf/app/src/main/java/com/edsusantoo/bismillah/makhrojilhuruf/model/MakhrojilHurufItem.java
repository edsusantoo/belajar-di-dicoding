package com.edsusantoo.bismillah.makhrojilhuruf.model;

import java.util.List;

public class MakhrojilHurufItem {

    private String tempat;

    private List<DataItem> data;

    public MakhrojilHurufItem(String tempat, List<DataItem> data) {
        this.tempat = tempat;
        this.data = data;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

}