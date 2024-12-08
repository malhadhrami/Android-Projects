package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Pharmacy extends AppCompatActivity {

    TextView textview;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        textview = findViewById(R.id.textView6);
        ImageButton back = findViewById(R.id.imageButton);

        // Creating a string that contains the information to be displayed
        String para = "Hospitals are committed to providing patients" +
                " with the highest quality healthcare, conveniently accessible" +
                "to industrial and residential communities in Abu Dhabi’s Mussafah. \n" +
                "\n" +
                "Each of our two multi-specialty hospitals is fully equipped with" +
                " state-of-the-art technology and more than 15 medical and surgical " +
                "specialties and clinical services. \n " +
                "\n" +
                "We believe every patient deserves cost-effective , specialized" +
                " , high-quality care. Our philosophy is to provide patients with " +
                "the right care, at the right time , and in the right place. " +
                "Our team of talented and compassionate physicians works" +
                " together to deliver high-quality healthcare that improves your health." +
                " We strive to offer an exceptional experience for every patient" +
                " and family member by providing a wide range of " + "\n" +
                "medical services across multiple specialties.\n" +
                "\n" +
                "Contact us: 050 8562 500" + "\n"+ "\n"+
                "Email: H00426013@hct.ac.ae"+ "\n" + "\n"
                ;

        // set value to the given TextView
        textview.setText(para);

        // to perform the movement action
        // Moves the cursor or scrolls to the
        // top or bottom of the document
        textview.setMovementMethod(new ScrollingMovementMethod());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pharmacy.this, Home.class);
                startActivity(intent);
            }
        });
    }
}