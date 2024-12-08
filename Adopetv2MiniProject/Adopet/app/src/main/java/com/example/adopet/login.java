package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private Button Register;

    private Button Login;

    private String NOTF_CH_ID = "Login notificaiton";

    private ImageView instagram;

    private ImageView twitter;

    private ImageView snap;

    private EditText username, password;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;



    private CheckBox remember_me;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        snap = findViewById(R.id.snap);

        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://t.snapchat.com/gbvuOhfO");
            }
        });




        twitter = findViewById(R.id.twitter);


        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://twitter.com/dzyuii");
            }
        });

        instagram =  findViewById(R.id.url_instagram);

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://www.instagram.com/deyaz.1/");
            }
        });




        sp = getSharedPreferences("login", MODE_PRIVATE);

        username = findViewById(R.id.Username_input);
        password = findViewById(R.id.Password_input);
        remember_me = findViewById(R.id.checkBox);

        Register =  findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity2();



            }
        });


        Login =  findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (remember_me.isChecked() == true){
                    saveLogin();
                } else {
                    deleteSP();
                }

                openActivity4();
                loginuser();

            }
        });

        getLogin();








    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void openActivity2(){

        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    public void openActivity4(){
        if (validation() & validationPassword()) {
            Toast.makeText(login.this, "Welcome Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(this, shop.class);
            startActivity(intent);

        }
    }


    private boolean validation() {

        String username_txt = username.getText().toString();

        if (username_txt.isEmpty()) {

            username.setError("Username is required!");
            return false;

        } else {
            return true;
        }

    }
    private boolean validationPassword() {

        String Password_pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,64})";

        String pass_text = password.getText().toString();
        if (pass_text.isEmpty()) {
            password.setError("Password is required! ");
            return false;
        } else if (!pass_text.matches(Password_pattern)) {
            password.setError("Wrong Password");
            return false;
        } else {
            return true;

        }

    }
    private void saveLogin(){
        editor = sp.edit();
        editor.putString("USERNAME_KEY", username.getText().toString());
        editor.putString("PASSWORD_KEY", password.getText().toString());
        editor.putBoolean("REMEMBER_KEY", remember_me.isChecked());

        editor.commit();
    }

    private void getLogin(){
        String uname = sp.getString("USERNAME_KEY", "");
        String pass = sp.getString("PASSWORD_KEY", "");
        boolean remember = sp.getBoolean("REMEMBER_KEY",false);

        username.setText(uname);
        password.setText(pass);
        remember_me.setChecked(remember);

    }

    private void deleteSP(){
        editor = sp.edit();
        editor.clear().commit();
    }

    private void loginuser() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(NOTF_CH_ID,
                    "cart", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTF_CH_ID);
            builder.setSmallIcon(R.drawable.baseline_pets_24);
            builder.setContentTitle("ADOPETv2");
            builder.setContentText("Successfully Logged in User : :" + username.getText().toString());
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);


            NotificationManagerCompat mc = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mc.notify(999, builder.build());

        }
    }



}