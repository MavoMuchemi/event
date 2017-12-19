package com.example.mathew.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {

    private ArrayList<Event> events;
    private Context context;

    public EventAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = (LayoutInflater.from(context)).inflate(R.layout.event_item, parent, false);
        TextView time = (TextView) view.findViewById(R.id.event_time);
        TextView name = (TextView) view.findViewById(R.id.event_name);

        Event event = events.get(position);

        time.setText(event.getTime());
        name.setText(event.getName());

        return  view;
    }


    @Override
    public int getCount() {
        return events.size();
    }
}
