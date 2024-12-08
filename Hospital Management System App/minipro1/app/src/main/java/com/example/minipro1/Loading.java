package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Loading.this, ThankYou.class);
                        Bundle b = ActivityOptions.makeSceneTransitionAnimation(Loading.this).toBundle();
                        startActivity(intent, b);
                    }
                });
            }
        }, 3000);

    }

}