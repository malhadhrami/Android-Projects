package com.example.minipro1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ThankYou extends AppCompatActivity {

    private static final String NOTF_CH_ID = "Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        notification();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThankYou.this, Home.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    private void notification() {
        // step 1 - create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(NOTF_CH_ID, "Message",
                    NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(nc);
        }

        // step 3 - building notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTF_CH_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_person_24);
        builder.setContentTitle("Message");
        builder.setContentText("The ambulance is on its way as soon as possible!");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        // step 4 - Notification manager
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(99, builder.build());
    }
}