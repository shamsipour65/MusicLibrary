package com.example.setareh.musiclibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<Track> {
    public Adapter(@NonNull Context context, @NonNull List<Track> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_music, parent, false);
        }

        Track track = getItem(position);

        ImageView picture = (ImageView) convertView.findViewById(R.id.picture);
        picture.setImageResource(track.getPicture());

        TextView title = (TextView)convertView.findViewById(R.id.musicTitle);
        title.setText(track.getTitle());

        return convertView;
    }
}
