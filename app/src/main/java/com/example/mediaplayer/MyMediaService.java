package com.example.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;

import java.io.IOException;
import java.util.List;

public class MyMediaService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayer;
    List<Song> songs;
    int songPosition;

    public MyMediaService() {
        songs = Utils.getListData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras().getInt("position") != -1) {
            songPosition = intent.getExtras().getInt("position");
            playSong();
        } else {
            mediaPlayer.stop();
        }
        return Service.START_STICKY;
    }

    private void playSong() {
        if (mediaPlayer == null) {
            mediaPlayerSettings();
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
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
            mediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        mediaPlayer.release();
    }
}
