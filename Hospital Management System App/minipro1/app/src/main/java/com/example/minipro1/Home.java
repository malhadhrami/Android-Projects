package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class Home extends AppCompatActivity {

    TextView myname;
    CardView doctorCardView;
    CardView appointmentCardView;
    CardView ambulanceCardView;
    CardView pharamacyCardView;
    Button signoutbtn;
    ImageButton profile;
    ImageButton bye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myname = findViewById(R.id.username);
        doctorCardView = findViewById(R.id.doc2);
        appointmentCardView = findViewById(R.id.app1);
        ambulanceCardView = findViewById(R.id.card4);
        pharamacyCardView = findViewById(R.id.card3);
        signoutbtn = findViewById(R.id.btn_singout);
        profile = findViewById(R.id.img_profile);
        bye = findViewById(R.id.exit_btn);

        String name = getIntent().getStringExtra("key_name");
        myname.setText(name);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        doctorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Doctors.class);
                startActivity(intent);
            }
        });

        appointmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Appointment.class);
                startActivity(intent);
            }
        });

        ambulanceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Home.this)
                        .setTitle("Call Ambulance")
                        .setMessage("Are you sure this is your location?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Home.this, Loading.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });


        pharamacyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Pharmacy.class);
                startActivity(intent);
            }
        });

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        bye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Home.this)
                        .setTitle("Exit the application")
                        .setMessage("Are you sure you want to exit the app?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });
    }
}