package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    TextView select_app;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        select_app = findViewById(R.id.select_app);
        message = getIntent().getStringExtra("message");
        select_app.setText(message);

    }
}