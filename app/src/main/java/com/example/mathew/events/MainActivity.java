package com.example.mathew.events;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView eventsListView;
    private Button add, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.image);

        Intent intent = getIntent();

        Drawable drawable = getResources().getDrawable(getResources()
                .getIdentifier(intent.getStringExtra("type"), "drawable", getPackageName()));

        final String eventType = intent.getStringExtra("type");

        imageView.setImageDrawable(drawable);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        add = (Button) findViewById(R.id.add_event);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddEvent.class).putExtra("type", eventType));
            }
        });

        eventsListView = (ListView) findViewById(R.id.events);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Event> events = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String evType = "";

                    switch (eventType) {
                        case "sporrts" :
                            evType = "Sports Event";
                            break;
                        case "corporate" :
                            evType = "Corporate Event";
                            break;
                        case "social" :
                            evType = "Social Event";
                            break;
                        case "academic" :
                            evType = "Academic Event";
                            break;
                    }

                    Event event = dataSnapshot1.getValue(Event.class);

                    Log.e("m", evType);
                    Log.e("s", event.getType());

                    if(event.getType().equals(evType)) {
                        events.add(event);
                    }

                }
                EventAdapter eventAdapter = new EventAdapter(MainActivity.this, events);
                eventsListView.setAdapter(eventAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
