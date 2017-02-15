package com.gulbalasalamov.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gulbala on 2/14/17.
 */

public class TimeService extends Service {
    Timer timer;
    Handler handler;
    final static long period = 5000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        handler = new Handler(Looper.getMainLooper());
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                giveInfo();
            }
        }, 0, period);
    }

    private void giveInfo() {
        long time = System.currentTimeMillis();
        SimpleDateFormat info = new SimpleDateFormat("dd MMMM yyyy, EEEE / HH:mm");
        final String result = info.format(new Date(time));
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TimeService.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
