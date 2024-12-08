package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RePassword extends AppCompatActivity {

    Button btn_submit;
    EditText pass_check;
    String getPassword = "";
    String getUsername = "";
    String getEmail = "";
    String getID = "";
    DBHelper dbHelper;
    String val = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_password);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btn_submit = findViewById(R.id.btn_submit);
        pass_check = findViewById(R.id.pass_check);
        dbHelper = new DBHelper(this);
        getUsername = getIntent().getStringExtra("username");
        getPassword = getIntent().getStringExtra("password");
        getEmail = getIntent().getStringExtra("email");
        getID = getIntent().getStringExtra("id");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePassword()) {
                    if (getPassword.contains(pass_check.getText().toString())){
                        Toast.makeText(RePassword.this, "Password must be different from previous", Toast.LENGTH_SHORT).show();
                    }else {
                        Users users = new Users(Integer.parseInt(getID), getUsername, getEmail, pass_check.getText().toString());
                        dbHelper.updatePassword(users);
                        Toast.makeText(RePassword.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RePassword.this, Login.class));
                        finish();
                    }
                }
            }
        });


    }

    private Boolean validatePassword() {
        String PasswordPattern =
                ("^" +
                        "(?=.*[0-9])" +            //at least 1 digit
                        "(?=.*[a-z])" +           //at least 1 lower case letter
                        "(?=.*[A-Z])" +           //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +       //any letter
                        "(?=.*[@#$%^&+.=])" +     //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$");

        val = pass_check.getText().toString().trim();
        if (val.isEmpty()) {
            pass_check.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(PasswordPattern)) {
            pass_check.setError("Password too weak");
            return false;
        } else {
            pass_check.setError(null);
            return true;
        }
    }
}