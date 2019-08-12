package com.edsusantoo.bismillah.myworkmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.edsusantoo.bismillah.myworkmanager.model.WeatherResponse;
import com.edsusantoo.bismillah.myworkmanager.network.RetrofitConfig;

import java.text.DecimalFormat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyWorkManagerService extends Worker {

    static final String TAG = MyWorkManagerService.class.getSimpleName();
    //Isikan dengan nama kota Anda;    p
    static String EXTRAS_CITY = "extras_city";


    public MyWorkManagerService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {

            String extras = getInputData().getString(EXTRAS_CITY);

            //mengecek data intent dari activity
            if (extras == null) {
                return Result.failure();
            } else if (extras.isEmpty()) {
                return Result.failure();
            }

            //mengecek jika proses async berhasil atau mengulang kembali
            if (getCurrentWeather(extras)) {
                return Result.success();
            } else {
                return Result.retry();
            }

        } catch (Throwable e) {
            Log.e(TAG, "Error WorkManager :", e);
            return Result.failure();
        }
    }

    private Boolean getCurrentWeather(String city) {
        final Boolean[] isSuccess = new Boolean[1];

        //Isikan dengan API Key Anda dari openweathermap;
        String APP_ID = "cc2abbd88025b40954a307538886869d";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + APP_ID;

        RetrofitConfig.getApiServices().getWeather(city, APP_ID)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                String currentWeather = response.body().getWeather().get(0).getMain();
                                String description = response.body().getWeather().get(0).getDescription();
                                double tempInKelvin = response.body().getMain().getTemp();
                                double tempInCelcius = tempInKelvin - 273;
                                String temprature = new DecimalFormat("##.##").format(tempInCelcius);

                                String title = "Current Weather";
                                String message = currentWeather + ", " + description + " with " + temprature + " celcius";
                                int notifId = 100;
                                showNotification(getApplicationContext(), title, message, notifId);
                                isSuccess[0] = true;
                            } else {
                                isSuccess[0] = false;
                            }
                        } else {
                            isSuccess[0] = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        isSuccess[0] = false;
                    }
                });
        return isSuccess[0];
    }


    private void showNotification(Context context, String title, String message, int notifId) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "Job service channel";

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_replay_30_black)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);

            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }
    }
}
