package com.example.mediaplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediaplayer.data.Song;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<Song> {

    private Context context;
    private int layoutResource;
    private List<Song> songs;

    public MyListAdapter(@NonNull Context context, int resource, @NonNull List<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.layoutResource = resource;
        this.songs = songs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layoutResource, null);
        ImageView img = convertView.findViewById(R.id.custom_adapter__img__song_picture);
        img.setImageResource(context.getResources().getIdentifier(songs.get(position).getImageId(), "drawable", context.getPackageName()));
        TextView title = convertView.findViewById(R.id.custom_adapter__tv__song_title);
        title.setText(songs.get(position).getTitle());
        return convertView;
    }
}
