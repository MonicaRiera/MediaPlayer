package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, View.OnClickListener {

    private MediaPlayer mediaPlayer;
    List<Song> songs;
    int songPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songs = Utils.getListData();
        //mediaPlayer = new MediaPlayer();
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        final ListView listView = findViewById(R.id.activity_main__list_view__data);
        listView.setAdapter(new MyListAdapter(this, R.layout.custom_adapter, songs));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Position " + position + " clicked", Toast.LENGTH_SHORT).show();
                setSongPosition(position);
                //playSong();
            }
        });

        final ImageButton btnPlay = findViewById(R.id.activity_main__btn__play);
        btnPlay.setOnClickListener(this);
        final ImageButton btnNext = findViewById(R.id.activity_main__btn__next);
        btnNext.setOnClickListener(this);
        final ImageButton btnPrevious = findViewById(R.id.activity_main__btn__previous);
        btnPrevious.setOnClickListener(this);

    }

    private void setSongPosition(int position) {
        this.songPosition = position;
        playSong();
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
        Toast.makeText(this, "Playing song" + songPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_main__btn__play) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            } else {
                playSong();
            }
        } else if (v.getId() == R.id.activity_main__btn__next) {
            if (songPosition < songs.size()-1) {
                setSongPosition(songPosition + 1);
                //playSong();
            } else {
                setSongPosition(0);
                //playSong();
            }
        } else if (v.getId() == R.id.activity_main__btn__previous) {
            if (songPosition > 0) {
                setSongPosition(songPosition - 1);
            } else {
                setSongPosition(songs.size()-1);
            }
        }
    }

    private void playSong() {
        if (mediaPlayer == null) {
            mediaPlayerSettings();
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            Toast.makeText(this, "Music stopped", Toast.LENGTH_SHORT).show();
            mediaPlayerSettings();
        }

        mediaPlayer.prepareAsync();
    }

    private void mediaPlayerSettings() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        final Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/raw/" + songs.get(songPosition).getSongId());
        try {
            mediaPlayer.setDataSource(getApplicationContext(), mediaPath);
            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
