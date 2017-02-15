package com.gulbalasalamov.servicedemo;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    private boolean isServiceWorking() {
        ActivityManager servisYoneticisi = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo servis : servisYoneticisi.getRunningServices(Integer.MAX_VALUE)) {
            if (getApplication().getPackageName().equals(servis.service.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public void btnClick(View view) {
        Button button = (Button) view;

        if (isServiceWorking()) {
            stopService(new Intent(this,TimeService.class));
            button.setText(getString(R.string.baslat));
        } else {
            startService(new Intent(this,TimeService.class));
            button.setText(getString(R.string.durdur));
        }
    }
}
