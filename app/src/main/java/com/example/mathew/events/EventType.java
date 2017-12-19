package com.example.mathew.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class EventType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayList<EventTypeEntity> eventTypeEntities = new ArrayList<>();

        String[] events = {"Sports", "Corporate", "Social", "Academic"};
        String[] images = {"sporrts", "corporate", "social", "academic"};

        for (int i = 0; i < events.length; i++) {
            EventTypeEntity ev = new EventTypeEntity();
            ev.setName(events[i]);
            ev.setImage(images[i]);
            eventTypeEntities.add(ev);
        }

        listView.setAdapter(new EventTypeAdapter(this, eventTypeEntities));

    }
}
