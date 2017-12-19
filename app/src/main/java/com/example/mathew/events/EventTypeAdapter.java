package com.example.mathew.events;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventTypeAdapter extends BaseAdapter {

    private ArrayList<EventTypeEntity> events;
    private Context context;

    public EventTypeAdapter(Context context, ArrayList<EventTypeEntity> events) {
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
        View view = (LayoutInflater.from(context)).inflate(R.layout.event_type_item, parent, false);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView name = (TextView) view.findViewById(R.id.name);

        final EventTypeEntity event = events.get(position);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", event.getImage());
                context.startActivity(intent);
            }
        });

        Drawable drawable = context.getResources().getDrawable(context.getResources()
                .getIdentifier(event.getImage(), "drawable", context.getPackageName()));

        image.setImageDrawable(drawable);
        name.setText(event.getName());

        return  view;
    }

    @Override
    public int getCount() {
        return events.size();
    }
}
