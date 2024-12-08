package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.*;
import java.util.Calendar;


public class Register extends AppCompatActivity {

    EditText mName;
    EditText mUsername;
    EditText mPassword;
    EditText mCpassword;
    EditText mEmail;
    EditText mBirthdate;
    Calendar calendar;
    Button bRegister;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mName = findViewById(R.id.txt_fullname);
        mUsername = findViewById(R.id.txt_reguname);
        mPassword = findViewById(R.id.pass_check);
        mCpassword = findViewById(R.id.con_pass);
        mEmail = findViewById(R.id.txt_email);
        mBirthdate = findViewById(R.id.date);
        bRegister = findViewById(R.id.btn_signup);
        dbHelper = new DBHelper(this);


        ImageButton backButton = findViewById(R.id.goback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


        mBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                TextView date;
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year,
                                                  int month, int day) {
                                mBirthdate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        },
                        year, month, dayOfMonth);
                // Set the maximal date supported as the current date
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateName() || !validateUsername() || !validateEmail() ||
                        !validatePassword() || !validateCPassword() || !validateBDay()) {
                    return;
                }

                // Get the text from the EditText fields
                String name = mName.getText().toString().trim();
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String birthdate = mBirthdate.getText().toString().trim();

                // Insert user data into the database
                Users users = new Users(username, email, password);
                boolean success = dbHelper.register(users);

                if (success) {
                    Toast.makeText(Register.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    Bundle b = ActivityOptions.makeSceneTransitionAnimation(Register.this).toBundle();
                    startActivity(intent, b);
                    finish();
                } else {
                    Toast.makeText(Register.this, "Error inserting data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateName() {
        String val = mName.getText().toString();
        if (val.isEmpty()) {
            mName.setError("Field cannot be empty");
            return false;
        } else {
            mName.setError(null);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = mUsername.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            mUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 10) {
            mUsername.setError("Username too long");
            return false;
        } else if (val.matches(noWhiteSpace)) {
            mUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            mUsername.setError(null);
            return true;
        }
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

            String val = mPassword.getText().toString().trim();
            if (val.isEmpty()) {
                mPassword.setError("Field cannot be empty");
                return false;
            } else if (!val.matches(PasswordPattern)) {
                mPassword.setError("Password too weak");
                return false;
            } else {
                mPassword.setError(null);
                return true;
            }
        }
        private Boolean validateCPassword() {
            String val1 = mCpassword.getText().toString().trim();
            String val2 = mPassword.getText().toString().trim();
            if(!val1.equals(val2))
            { mCpassword.setError("password not conform");
                return false;
            }
            else {
                mCpassword.setError(null);
                return true;
            }
        }

        private Boolean validateEmail() {
            String val = mEmail.getText().toString();
            String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+" +
                                    "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+$";
            if (val.isEmpty()) {
                mEmail.setError("Field cannot be empty");
                return false;
            } else if (!val.matches(emailPattern)) {
                mEmail.setError("Invalid email address");
                return false;
            } else {
                mEmail.setError(null);
                return true;
            }
        }

        private boolean validateBDay(){
            String val =mBirthdate.getText().toString();
            if (val.isEmpty())
            {mBirthdate.setError("Field must not be empty");
                return false;}
            else {
                mBirthdate.setError(null);
                return true;}
        }


    }