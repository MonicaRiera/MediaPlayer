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

    private List<Song> songs = Utils.getListData();
    private int songPosition;

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
                generateNotification();
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

    private void generateNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channelId");
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);

        builder.setSmallIcon(R.mipmap.ic_launcher_speaker)
                .setContentTitle("Media Notification")
                .setContentText("Notification image")
                .setAutoCancel(true)
                .setContentIntent(getPendingIntentWithRequestCode(23));

        RemoteViews customNotification = new RemoteViews(getPackageName(), R.layout.custom_notification);
        customNotification.setImageViewResource(R.id.custom_notification__img__speaker, R.mipmap.ic_launcher_speaker);
        customNotification.setTextViewText(R.id.custom_notification__tv__header, "Now playing...");
        customNotification.setTextViewText(R.id.custom_notification__tv__song_title, songs.get(songPosition).getTitle());
        customNotification.setTextViewText(R.id.custom_notification__tv__song_info, "Originary from " + songs.get(songPosition).getCountry());
        builder.setContent(customNotification);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel =
                    new NotificationChannel("NOTIFICATION_CHANNEL_ID",
                            "NOTIFICATION_CHANNEL_NAME",
                            importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            builder.setChannelId("NOTIFICATION_CHANNEL_ID");
            notificationManager.createNotificationChannel(notificationChannel);

        }

        notificationManager.notify(1, builder.build());
    }

    private PendingIntent getPendingIntentWithRequestCode(int requestCode) {
        Intent notificationIntent = new Intent(this, SecondActivity.class);
        return PendingIntent.getActivity(this, requestCode, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main__btn__play:
                generateIntent(songPosition);
                generateNotification();
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
                generateNotification();
                break;
            case R.id.activity_main__btn__previous:
                if (songPosition > 0) {
                    generateIntent(songPosition - 1);
                } else {
                    generateIntent(songs.size()-1);
                }
                generateNotification();
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
