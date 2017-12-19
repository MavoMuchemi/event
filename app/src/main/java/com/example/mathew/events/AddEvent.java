package com.example.mathew.events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEvent extends AppCompatActivity {

    private EditText time, name, location;
    private Button button;
    private Spinner spinner;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        name = (EditText) findViewById(R.id.event_Name);
        spinner = (Spinner) findViewById(R.id.event_type);
        time = (EditText) findViewById(R.id.event_time);
//        name = (EditText) findViewById(R.id.event_name);
        location = (EditText) findViewById(R.id.event_location);
        button = (Button) findViewById(R.id.add_event);

        Intent intent = getIntent();
        final String eventType = intent.getStringExtra("type");

        Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = String.format("%s-%s-%s", year, month + 1, dayOfMonth);
                time.setText(date);
            }
        }, calendar.get(Calendar.DAY_OF_YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
                event.setType(spinner.getSelectedItem().toString());
                event.setLocation(location.getText().toString());
                event.setName(name.getText().toString());
                event.setTime(time.getText().toString());
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                String key = databaseReference.child("events").push().getKey();
                databaseReference.child("events").child(key).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddEvent.this, "Added event successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddEvent.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("type", eventType);
                        startActivity(intent);
                        startActivity(new Intent(AddEvent.this, MainActivity.class));
                    }
                });
            }
        });
    }
}
