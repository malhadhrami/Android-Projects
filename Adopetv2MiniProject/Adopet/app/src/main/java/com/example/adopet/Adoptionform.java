package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Adoptionform extends AppCompatActivity {

    private Button submit;

    private EditText name, age, location;

    private String NOTF_CH_ID = "Form notificaiton";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoptionform);


        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        location = findViewById(R.id.location);


        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation() & validation_age() & validation_location())


                    submit();

            }
        });


    }

    public void submit() {

        Intent intent = new Intent(this, SubmitFeedBack.class);
        startActivity(intent);
        Formdone();
    }

    private boolean validation() {

        String username_txt = name.getText().toString();

        if (username_txt.isEmpty()) {

            name.setError("Name is required!");
            return false;

        } else {
            return true;
        }

    }

    private boolean validation_age() {

        String username_txt = age.getText().toString();

        if (username_txt.isEmpty()) {

            age.setError("Age is required!");
            return false;

        } else {
            return true;
        }

    }

    private boolean validation_location() {

        String username_txt = location.getText().toString();

        if (username_txt.isEmpty()) {

            location.setError("Location is required!");
            return false;

        } else {
            return true;
        }

    }

    private void Formdone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(NOTF_CH_ID,
                    "cart", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTF_CH_ID);
            builder.setSmallIcon(R.drawable.baseline_pets_24);
            builder.setContentTitle("ADOPETv2");
            builder.setContentText("Successfully Sent Form Mr :" + name.getText().toString());
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);


            NotificationManagerCompat mc = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mc.notify(999, builder.build());

        }
    }


}