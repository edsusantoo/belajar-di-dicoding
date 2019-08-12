package com.edsusantoo.bismillah.myworkmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSetScheduler, btnCancelScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCancelScheduler = findViewById(R.id.btn_cancel_scheduler);
        btnSetScheduler = findViewById(R.id.btn_set_scheduler);
        btnSetScheduler.setOnClickListener(this);
        btnCancelScheduler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_scheduler:
                Data dataCity = new Data.Builder()
                        .putString(MyWorkManagerService.EXTRAS_CITY, "Jakarta")
                        .build();
                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresCharging(false)
                        .build();

                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorkManagerService.class, 15, TimeUnit.SECONDS)
                        .setInputData(dataCity)
                        .setConstraints(constraints)
                        .build();
                WorkManager.getInstance().enqueueUniquePeriodicWork(MyWorkManagerService.TAG, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
                break;
            case R.id.btn_cancel_scheduler:
                WorkManager.getInstance().cancelUniqueWork(MyWorkManagerService.TAG);
                break;
        }
    }
}
