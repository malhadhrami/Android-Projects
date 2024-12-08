package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SubmitFeedBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_feed_back);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.formdone);
        mediaPlayer.start();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SubmitFeedBack.this, shop.class);
                        startActivity(intent);
                    }
                });
            }
        }, 3000);
    }
}