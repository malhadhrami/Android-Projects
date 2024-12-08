package com.example.minipro1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class ForgetPassword extends AppCompatActivity {

    Button btn_nextforget;
    EditText etUsername;
    DBHelper dbHelper;
    ImageView backView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btn_nextforget = findViewById(R.id.btn_nextforget);
        etUsername = findViewById(R.id.etUsername);
        dbHelper = new DBHelper(this);
        backView = findViewById(R.id.goback2);
        btn_nextforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag=false;
                String getUsername = etUsername.getText().toString();
                if (getUsername.isEmpty()){
                    Toast.makeText(ForgetPassword.this, "Please enter username", Toast.LENGTH_SHORT).show();
                }
                    List<Users> list = dbHelper.getAllUsers();
                    for (Users users:list){
                        if(getUsername.equals(users.getUserName()) ){
                            flag=true;
                            Intent intent = new Intent(ForgetPassword.this, RePassword.class);
                            intent.putExtra("username", getUsername);
                            intent.putExtra("email", users.getEmail());
                            intent.putExtra("password", users.getPassword());
                            intent.putExtra("id", String.valueOf(users.getId()));
                            startActivity(intent);
                        }
                    }
                if (!flag){
                    Toast.makeText(ForgetPassword.this, "Please Check Username, Its not exists", Toast.LENGTH_SHORT).show();

                }
            }
        });

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassword.this, Login.class);
                startActivity(intent);
            }
        });
    }
}