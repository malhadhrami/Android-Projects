package com.example.adopet;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class shop extends AppCompatActivity {

    BottomAppBar bottomAppBar;





    TextView Adoption;

    TextView Settings;
    TextView ACCm;


    TextView p1;
    TextView p2;
    TextView p3;
    TextView p4;
    TextView p5;
    TextView p6;


    private ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);




        Adoption = findViewById(R.id.Adoption);
        Adoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openp1();
            }
        });

        Settings = findViewById(R.id.Settings);
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                construction();
            }
        });



        ACCm = findViewById(R.id.ACCm);


        ACCm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                construction();
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactUs();

            }
        });

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);
        p6 = findViewById(R.id.p6);


        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openp1();
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openp2();
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openp3();
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openp4();
            }
        });

        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openp5();
            }
        });

        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openp6();
            }
        });

        bottomAppBar=findViewById(R.id.bottomBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.home)
                {
                    Toast.makeText(shop.this, " You are at the home Page Already ", Toast.LENGTH_LONG).show();

                }
                if (item.getItemId()==R.id.Exit)
                {
                    Toast.makeText(shop.this, " Good Bye ", Toast.LENGTH_SHORT).show();


                    exit();
                }


                return false;
            }
        });

        button =  findViewById(R.id.hct);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://hct.ac.ae/ar/");
            }
        });


    }


    public void openp1(){
        Intent intent = new Intent(this, p1.class);
        startActivity(intent);
    }
    public void openp2(){
        Intent intent = new Intent(this, p2.class);
        startActivity(intent);
    }
    public void openp3(){
        Intent intent = new Intent(this, p3.class);
        startActivity(intent);
    }
    public void openp4(){
        Intent intent = new Intent(this, p4.class);
        startActivity(intent);
    }
    public void openp5(){
        Intent intent = new Intent(this, p5.class);
        startActivity(intent);
    }
    public void openp6(){
        Intent intent = new Intent(this, p6.class);
        startActivity(intent);
    }
    public void openContactUs(){
        Intent intent = new Intent(this, contactus.class);
        startActivity(intent);
    }

    public void exit() {
        new AlertDialog.Builder(shop.this)
                .setTitle("Exit for the application")
                .setMessage("Are you sure to exit from the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeApp();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                })
                .setCancelable(true)
                .show();

    }

    public void closeApp() {
        finishAffinity();
        System.exit(0);
    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void construction(){
        Intent intent = new Intent(this, Construction.class);
        startActivity(intent);
    }



}





