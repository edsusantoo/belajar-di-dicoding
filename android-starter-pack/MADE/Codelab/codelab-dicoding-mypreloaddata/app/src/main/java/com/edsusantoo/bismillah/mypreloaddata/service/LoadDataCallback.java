package com.edsusantoo.bismillah.mypreloaddata.service;

public interface LoadDataCallback {
    void onPreLoad();

    void onProgressUpdate(long progress);

    void onLoadSuccess();

    void onLoadFailed();

    void onLoadCancel();
}
