package com.example.mediaplayer.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.example.mediaplayer.MyMediaService;
import com.example.mediaplayer.R;
import com.example.mediaplayer.SecondActivity;
import com.example.mediaplayer.data.Song;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Song> getListData() {

        String[] listImages = {"brasilsamba", "countryboy", "india", "littleplanet", "psychedelic", "relaxing", "theelevatorbossanova"};

        String[] listSongs = {"bensoundbrazilsamba", "bensoundcountryboy", "bensoundindia",
                "bensoundlittleplanet", "bensoundpsychedelic", "bensoundrelaxing", "bensoundtheelevatorbossanova"};

        String[] listTitles = {"Brazil Samba", "Country Boy", "India",
                "Little Planet", "Psychedelic", "Relaxing", "The Elevator Bossa Nova"};

        int[] listDurations = {240000, 207000, 253000, 396000, 236000, 288000, 254000};

        String[] listCountries = {"Brazil", "USA", "India", "Iceland", "South Korea", "Indonesia", "Brazil"};

        String[] listComments = {"Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade and African religious traditions, particularly of Angola",
                "Country music is a genre of American popular music that originated in the Southern United States in the 1920s",
                "The music of India includes multiple varieties of folk music, pop, and Indian classical music. India's classical music tradition, including Hindustani music and Carnatic, has a history spanning millennia and developed over several eras",
                "The music of Iceland includes vibrant folk and pop traditions. Wellknown artists from Iceland include medieval music group Voces Thules, alternative rock band The Sugarcubes, singers Björk and Emiliana Torrini, post-rock band Sigur Rós and indie folk/indie pop band Of Monsters and Men",
                "The Music of South Korea has evolved over the course of the decades since the end of the Korean War, and has its roots in the music of the Korean people, who have inhabited the Korean peninsula for over a millennium. Contemporary South Korean music can be divided into three different main categories: Traditional Korean folk music, popular music, or K-pop, and Western-influenced non-popular music",
                "The music of Indonesia demonstrates its cultural diversity, the local musical creativity, as well as subsequent foreign musical influences that shaped contemporary music scenes of Indonesia. Nearly thousands of Indonesian islands having its own cultural and artistic history and character",
                "Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade and African religious traditions, particularly of Angola"};

        LatLng[] listLocations = {new LatLng(-23.56,-46.63), new LatLng(30.27,-97.78), new LatLng(28.62,77.18), new LatLng(65.02,-18.86), new LatLng(37.56,126.99), new LatLng(-6.20,106.84), new LatLng(-23.56,-46.63)};

        List<Song> mySongList = new ArrayList<>();

        for (int i = 0; i < listTitles.length; i++ ) {
            Song song = new Song(listImages[i], listSongs[i], listTitles[i], listDurations[i], listCountries[i], listComments[i], listLocations[i]);
            mySongList.add(song);
        }
        return mySongList;

    }

    public static void generateNotification(Context context, int songPosition) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);

        builder.setSmallIcon(R.mipmap.ic_launcher_speaker)
                .setContentTitle("Media Notification")
                .setContentText("Notification image")
                .setAutoCancel(true)
                .setContentIntent(getPendingIntentWithRequestCode(23, context, songPosition));

        RemoteViews customNotification = new RemoteViews(context.getPackageName(), R.layout.custom_notification);
        customNotification.setImageViewResource(R.id.custom_notification__img__speaker, R.mipmap.ic_launcher_speaker);
        customNotification.setTextViewText(R.id.custom_notification__tv__header, "Now playing...");
        customNotification.setTextViewText(R.id.custom_notification__tv__song_title, getListData().get(songPosition).getTitle());
        customNotification.setTextViewText(R.id.custom_notification__tv__song_info, "Originary from " + getListData().get(songPosition).getCountry());
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

    public static PendingIntent getPendingIntentWithRequestCode(int requestCode, Context context, int songPosition) {
        Intent notificationIntent = new Intent(context, SecondActivity.class);
        notificationIntent.putExtra("position", songPosition);
        return PendingIntent.getActivity(context, requestCode, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }



}
