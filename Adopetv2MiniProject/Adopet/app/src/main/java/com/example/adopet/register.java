package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class register extends AppCompatActivity {
    private Button Register;
    private CheckBox checkBox;

    private String NOTF_CH_ID = "Register notificaiton";

    private ImageView instagram;

    private ImageView twitter;

    private ImageView snap;

    private MaterialAlertDialogBuilder materialAlertDialogBuilder;

    private EditText username, email, password,phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);



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



        checkBox = findViewById(R.id.checkBox2);




        Register = findViewById(R.id.Register);
        Register.setEnabled(false);
        username = findViewById(R.id.edtxt_username);
        email = findViewById(R.id.edtxt_email);
        password = findViewById(R.id.edtxt_password);
        phone = findViewById(R.id.Phone_number);

        materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);





        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    materialAlertDialogBuilder.setTitle("Terms and Conditions");
                    materialAlertDialogBuilder.setMessage("1.The content of the pages of this App is for your general information and use only. It is subject to change without notice.\n" +"\n" +
                            "2.Neither we nor any third parties provide any warranty or guarantee as to the accuracy, timeliness, performance, completeness or suitability of the information and materials found or offered on this App for any particular purpose. You acknowledge that such information and materials may contain inaccuracies or errors and we expressly exclude liability for any such inaccuracies or errors to the fullest extent permitted by law.\n" +"\n" +
                            "3.Your use of any information or materials on this App is entirely at your own risk, for which we shall not be liable. It shall be your own responsibility to ensure that any products, services or information available through this App meet your specific requirements.\n" +"\n" +
                            "4.This App contains material which is owned by or licensed to us. This material includes, but is not limited to, the design, layout, look, appearance and graphics. Reproduction is prohibited other than in accordance with the copyright notice, which forms part of these terms and conditions.\n" +"\n" +
                            "5.All trademarks reproduced in this App which are not the property of, or licensed to, the operator are acknowledged on the App.\n" +"\n" +
                            "6.Unauthorised use of this App may give rise to a claim for damages and/or be a criminal offence.\n" + "\n" +
                            "7.From time to time this App may also include links to other Apps. These links are provided for your convenience to provide further information. They do not signify that we endorse the App(s). We have no responsibility for the content of the linked App(s).\n" +"\n" +
                            "8.You may not create a link to this App from another App or document without the ADOPET Animal Shelter’s prior written consent.\n" +"\n" +
                            "9.Your use of this App and any dispute arising out of such use of the App is subject to the laws of United Arab Emirates.\n" +"\n" +
                            "10.Through this App you are able to link to other Apps which are not under the control of ADOPET Animal Shelter. We have no control over the nature, content and availability of those sites. The inclusion of any links does not necessarily imply a recommendation or endorse the views expressed within them.\n" +"\n" +
                            "11.Every effort is made to keep the App up and running smoothly. However, ADOPET Animal Shelter takes no responsibility for, and will not be liable for, the App being temporarily unavailable due to technical issues beyond our control.");
                    materialAlertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Register.setEnabled(true);
                            dialogInterface.dismiss();

                        }
                    });
                    materialAlertDialogBuilder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            checkBox.setChecked(false);
                        }
                    });

                    materialAlertDialogBuilder.show();

                }else{
                    Register.setEnabled(false);
                }

            }
        });



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation() & validationEmail() & validationPassword() & validationPhone()) {
                    Toast.makeText(register.this, "All Good", Toast.LENGTH_SHORT)
                            .show();


                    Intent intent = new Intent(register.this, shop.class);
                    startActivity(intent);
                    RegisterDone();
                    


                }
            }
        });
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

    private boolean validationPassword() {

        String Password_pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,64})";

        String pass_text = password.getText().toString();
        if (pass_text.isEmpty()) {
            password.setError("Password is required! ");
            return false;
        } else if (!pass_text.matches(Password_pattern)) {
            password.setError("Weak Password!");
            return false;
        } else {
            return true;

        }

    }

    private boolean validationPhone(){
        String Phone_pattern ="(\\+?( |-|\\.)?\\d{1,2}( |-|\\.)?)?(\\(?\\d{3}\\)?|\\d{3})( |-|\\.)?(\\d{3}( |-|\\.)?\\d{4})";

        String phone_number = phone.getText().toString();
        if (phone_number.isEmpty()) {
            phone.setError("Enter Your Phone Number Please!");
            return false;
        }else if (!phone_number.matches(Phone_pattern)) {
            phone.setError("Enter a correct Phone Number");
            return false;
        }else{
            return true;

        }

    }
    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void RegisterDone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(NOTF_CH_ID,
                    "cart", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTF_CH_ID);
            builder.setSmallIcon(R.drawable.baseline_pets_24);
            builder.setContentTitle("ADOPETv2");
            builder.setContentText("Successfully Registered User :" + username.getText().toString());
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);


            NotificationManagerCompat mc = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mc.notify(999, builder.build());

        }
    }

}




















