package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int songPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.activity_main__list_view__data);
        listView.setAdapter(new MyListAdapter(this, R.layout.custom_adapter, Utils.getListData()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                songPosition = position;
                Utils.generateIntent(MainActivity.this, songPosition);
                Utils.generateNotification(MainActivity.this, songPosition);
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
                Utils.generateIntent(this, songPosition);
                Utils.generateNotification(this, songPosition);
                break;
            case R.id.activity_main__btn__pause:
                Utils.generateIntent(this, -1);
                break;
            case R.id.activity_main__btn__next:
                if (songPosition < Utils.getListData().size()-1) {
                    songPosition += 1;
                    Utils.generateIntent(this, songPosition);
                } else {
                    songPosition = 0;
                    Utils.generateIntent(this, songPosition);
                }
                Utils.generateNotification(this, songPosition);
                break;
            case R.id.activity_main__btn__previous:
                if (songPosition > 0) {
                    songPosition -= 1;
                    Utils.generateIntent(this, songPosition);
                } else {
                    songPosition = Utils.getListData().size()-1;
                    Utils.generateIntent(this, songPosition);
                }
                Utils.generateNotification(this, songPosition);
                break;
                default:
                    break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
        }
    }
}
