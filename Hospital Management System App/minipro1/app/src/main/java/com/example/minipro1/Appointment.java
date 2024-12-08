package com.example.minipro1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Appointment extends AppCompatActivity {

    RecyclerView recyclerView;
    Button requestAppointment;
    EditText timeET;
    EditText dateET;
    Spinner app_spinner, doc_spinner;
    String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";
    String timeFormatRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    String strAppointment, strDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

//        recyclerView = findViewById(R.id.doctors_list);
        requestAppointment = findViewById(R.id.btn_requestAppointment);
        timeET = findViewById(R.id.timeET);
        dateET = findViewById(R.id.dateET);
        app_spinner = findViewById(R.id.app_spinner);
        doc_spinner = findViewById(R.id.doc_spinner);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        String[] doctorsArray = getResources().getStringArray(R.array.doctors_array);
        String[] appointmentArray = getResources().getStringArray(R.array.appointment_types);
        ArrayAdapter appointmentAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, appointmentArray);
        appointmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        app_spinner.setAdapter(appointmentAdapter);

        app_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAppointment = appointmentArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter doctorAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, doctorsArray);
        appointmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doc_spinner.setAdapter(doctorAdapter);

        doc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDoctor = doctorsArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        DoctorListAdapter doctorListAdapter = new DoctorListAdapter(doctorsArray);
//        app_spinner.setAdapter(doctorListAdapter);

        requestAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateET.getText().toString();
                String time = timeET.getText().toString();
                if (time.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter time", Toast.LENGTH_SHORT).show();
                    return;
                } else if (date.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter date", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isValidDate = date.matches(dateFormatRegex);
                boolean isValidTime = Pattern.compile(timeFormatRegex).matcher(time).matches();
                if (!isValidDate) {
                    Toast.makeText(getApplicationContext(), "Enter Valid date", Toast.LENGTH_SHORT).show();
                } else if (!isValidTime) {
                    Toast.makeText(getApplicationContext(), "Enter Valid time", Toast.LENGTH_SHORT).show();
                }else if (strAppointment.contains("Select type")) {
                    Toast.makeText(getApplicationContext(), "Please select appointment type", Toast.LENGTH_SHORT).show();
                }else if (strDoctor.contains("Select doctor")) {
                    Toast.makeText(getApplicationContext(), "Please select doctor", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Appointment.this, AppointmentBookedActivity.class);
                    intent.putExtra("time", time);
                    intent.putExtra("date", date);
                    intent.putExtra("appointment", strAppointment);
                    intent.putExtra("doctor", strDoctor);
                    startActivity(intent);
                }
            }
        });
    }
}
