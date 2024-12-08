package com.example.minipro1;

import static com.example.minipro1.DBHelper.COLUMN_USERNAME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Login extends AppCompatActivity {

    EditText name;
    EditText password;
    Button login;
    TextView register;
    DBHelper dbHelper;
    TextView forget;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    CheckBox remember_me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        name = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_pass);
        login = findViewById(R.id.btn_signin);
        register = findViewById(R.id.txt_signup);
        forget = findViewById(R.id.txt_forgetpass);
        dbHelper = new DBHelper(this);
        sp = getSharedPreferences("Login", this.MODE_PRIVATE);
        remember_me = findViewById(R.id.chk_box);

        // Set OnClickListener on the register TextView
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start MainActivity3
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start MainActivity3
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
    }

    public Cursor getData(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + dbHelper.getUsernameColumnName() + " = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void login(View view) {
        String username = name.getText().toString();
        String password = this.password.getText().toString();
        Cursor cursor = getData(username);
        Boolean flag=false;
        if (cursor.getCount() == 0) {
            Toast.makeText(Login.this, "No entries Exists", Toast.LENGTH_LONG).show();
        }
            List<Users> list = dbHelper.getAllUsers();
            for (Users users:list){
                if(username.equals(users.getUserName()) && password.equals(users.getPassword())){
                    flag=true;
                    Intent intent = new Intent(this, Home.class);
                    intent.putExtra("key_name", username);
                    Bundle b = ActivityOptions.makeSceneTransitionAnimation(Login.this).toBundle();
                    startActivity(intent, b);
                }
            }
        if (remember_me.isChecked() == true){
            saveLogin();
        } else {
            deleteSP();
        }

        getLogin();

        if (!flag){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setCancelable(true);
            builder.setTitle("Wrong Credential");
            builder.setMessage("Username and password invalid");
            builder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }

        dbHelper.close();
    }

    private void saveLogin(){
        editor = sp.edit();
        editor.putString("USERNAME_KEY", name.getText().toString());
        editor.putString("PASSWORD_KEY", password.getText().toString());
        editor.putBoolean("REMEMBER_KEY", remember_me.isChecked());

        editor.commit();
    }

    private void getLogin(){
        String uname = sp.getString("USERNAME_KEY", "");
        String pass = sp.getString("PASSWORD_KEY", "");
        boolean remember = sp.getBoolean("REMEMBER_KEY",false);

        name.setText(uname);
        password.setText(pass);
        remember_me.setChecked(remember);

    }

    private void deleteSP(){
        editor = sp.edit();
        editor.clear().commit();
    }



    public static boolean loginCheck(Cursor cursor, String username, String password) {
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(username)) {
                if (cursor.getString(2).equals(password)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
