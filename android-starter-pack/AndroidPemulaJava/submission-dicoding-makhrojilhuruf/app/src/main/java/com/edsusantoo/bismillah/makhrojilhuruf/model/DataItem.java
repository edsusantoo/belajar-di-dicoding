package com.edsusantoo.bismillah.makhrojilhuruf.model;


import android.os.Parcel;
import android.os.Parcelable;

public class DataItem implements Parcelable {

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
    private String huruf;
    private String penjelasan;
    private int gambar;
    private String arab;

    public DataItem() {

    }

    public DataItem(String huruf, String arab, int gambar, String penjelasan) {
        this.huruf = huruf;
        this.penjelasan = penjelasan;
        this.gambar = gambar;
        this.arab = arab;
    }

    private DataItem(Parcel in) {
        this.huruf = in.readString();
        this.penjelasan = in.readString();
        this.gambar = in.readInt();
        this.arab = in.readString();
    }

    public String getHuruf() {
        return huruf;
    }

    public void setHuruf(String huruf) {
        this.huruf = huruf;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getArab() {
        return arab;
    }

    public void setArab(String arab) {
        this.arab = arab;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.huruf);
        dest.writeString(this.penjelasan);
        dest.writeInt(this.gambar);
        dest.writeString(this.arab);
    }
}