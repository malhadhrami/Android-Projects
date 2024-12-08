package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppointmentBookedActivity extends AppCompatActivity {

    String date, time, appointmentType, doctor;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_booked);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        home = findViewById(R.id.btn_home);

        if (getIntent() != null) {
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
            appointmentType = getIntent().getStringExtra("appointment");
            doctor = getIntent().getStringExtra("doctor");
        }

        addNotification();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start Home activity
                Intent intent = new Intent(AppointmentBookedActivity.this, Home.class);
                startActivity(intent);
                finish(); // finish the current activity
            }
        });
    }

    private void addNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        String message = "Your appointment time is " + time + "\nand date is " + date + ",  and \nyou select " + appointmentType + " with doctor " + doctor;
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "MyNotification")
                        .setSmallIcon(R.drawable.ic_baseline_person_24)
                        .setContentTitle("Appointment")
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(AppointmentBookedActivity.this);
        notificationManager.notify(1, builder.build());
    }
}
