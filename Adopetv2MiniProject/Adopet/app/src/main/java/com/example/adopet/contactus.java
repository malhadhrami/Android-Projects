package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class contactus extends AppCompatActivity {

    private Button submit;
    private EditText email ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);
        email = findViewById(R.id.edtxt_email);
        Button submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validationEmail()) {
                    Toast.makeText(contactus.this, "Our customer service will be in touch", Toast.LENGTH_LONG)
                            .show();
                    startActivity(new Intent(contactus.this, shop.class));
                }
            }
        });

    }
            private boolean validationEmail() {
            String Email_pattern = "([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}";

            String email_txt = email.getText().toString();

            if (email_txt.isEmpty()) {

                email.setError("Email is required");
                return false;


            } else if (!email_txt.matches(Email_pattern)) {
                email.setError("Invalid Email Format");
                return false;


            } else {
                return true;
            }
        }

    }

