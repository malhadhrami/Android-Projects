package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Doctors extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageButton backButton = findViewById(R.id.gohome);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doctors.this, Home.class);
                startActivity(intent);
            }
        });
    }
}