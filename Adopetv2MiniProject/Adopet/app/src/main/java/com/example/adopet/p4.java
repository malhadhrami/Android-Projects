package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class p4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private TextView mItemDescription;
    private ImageView mItemPicture;

    private Button showphone;

    private Spinner mItemSpinner;

    private Button Apply;
    private BottomAppBar bottomAppBar;


    private Item[] mItems = {
            new Item("Type 1", "Shitzu female puppy\nDate of Birth : 22/12/2022\nVaccines and microchip", R.drawable.p4),
            new Item("Type 2", "2 year old very friendly dog", R.drawable.p4_1),
            new Item("Type 3", "Female labrador puppy\n4 months old\nvaccination and passport", R.drawable.p4_2)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactUs();

            }
        });

        bottomAppBar = findViewById(R.id.bottomBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Toast.makeText(p4.this, " Home Page ", Toast.LENGTH_SHORT).show();

                    openp6();

                }
                if (item.getItemId() == R.id.Exit) {



                    exit();

                }


                return false;
            }
        });
        Apply = findViewById(R.id.Apply);


        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Apply();
            }
        });

        showphone = findViewById(R.id.ShowPhoneNumber);

        showphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0553981677";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                startActivity(intent);
            }
        });

        mItemDescription = findViewById(R.id.product_description);
        mItemPicture = findViewById(R.id.product_p1);
        mItemSpinner = findViewById(R.id.Type_Spinner);


        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mItemSpinner.setAdapter(adapter);

        mItemSpinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = (Item) adapterView.getItemAtPosition(i);

        mItemDescription.setText(item.getDescription());
        mItemPicture.setImageResource(item.getImageResource());
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }

    public void Apply() {

        Intent intent = new Intent(this, Adoptionform.class);
        startActivity(intent);
    }

    public void exit() {
        new AlertDialog.Builder(p4.this)
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

    public void openp6() {
        Intent intent = new Intent(this, shop.class);
        startActivity(intent);
    }

    public void openContactUs(){
        Intent intent = new Intent(this, contactus.class);
        startActivity(intent);
    }
}