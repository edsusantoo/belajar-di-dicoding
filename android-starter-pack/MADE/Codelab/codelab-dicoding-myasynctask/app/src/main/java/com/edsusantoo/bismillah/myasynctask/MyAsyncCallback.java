package com.edsusantoo.bismillah.myasynctask;

public interface MyAsyncCallback {
    void onPreExecute();

    void onPostExecute(String text);
}
