package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Song> songs = Utils.getListData();
    int songPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.activity_main__list_view__data);
        listView.setAdapter(new MyListAdapter(this, R.layout.custom_adapter, songs));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                generateIntent(position);
            }
        });

        final ImageButton btnPlay = findViewById(R.id.activity_main__btn__play);
        btnPlay.setOnClickListener(this);
        final ImageButton btnPause = findViewById(R.id.activity_main__btn__pause);
        btnPause.setOnClickListener(this);
        final ImageButton btnNext = findViewById(R.id.activity_main__btn__next);
        btnNext.setOnClickListener(this);
        final ImageButton btnPrevious = findViewById(R.id.activity_main__btn__previous);
        btnPrevious.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main__btn__play:
                generateIntent(songPosition);
                break;
            case R.id.activity_main__btn__pause:
                generateIntent(-1);
                break;
            case R.id.activity_main__btn__next:
                if (songPosition < songs.size()-1) {
                    generateIntent(songPosition + 1);
                } else {
                    generateIntent(0);
                }
                break;
            case R.id.activity_main__btn__previous:
                if (songPosition > 0) {
                    generateIntent(songPosition - 1);
                } else {
                    generateIntent(songs.size()-1);
                }
                break;
                default:
                    break;
        }
    }

    private void generateIntent(int position) {
        if (position != -1) {
            this.songPosition = position;
        }
        Intent intent = new Intent(this, MyMediaService.class);
        intent.putExtra("position", position);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
        }
    }
}
