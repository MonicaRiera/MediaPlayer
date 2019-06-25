package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imgSongPic = findViewById(R.id.activity_second__img__song_image);
        TextView tvTitle = findViewById(R.id.activity_second__tv__song_title);
        TextView tvComments = findViewById(R.id.activity_second__tv__comments);

        List<Song> songs = Utils.getListData();
        int position = getIntent().getExtras().getInt("position");

        imgSongPic.setImageResource(this.getResources().getIdentifier(songs.get(position).getImageId(), "drawable", this.getPackageName()));
        tvTitle.setText(songs.get(position).getTitle());
        tvComments.setText(songs.get(position).getComments());
    }
}
