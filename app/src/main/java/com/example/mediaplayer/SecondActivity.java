package com.example.mediaplayer;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class SecondActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap map;
    private int songPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imgSongPic = findViewById(R.id.activity_second__img__song_image);
        TextView tvTitle = findViewById(R.id.activity_second__tv__song_title);
        TextView tvComments = findViewById(R.id.activity_second__tv__comments);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_second__fr__map);
        mapFragment.getMapAsync(this);

        final ImageButton btnPlay = findViewById(R.id.activity_second__btn__play);
        btnPlay.setOnClickListener(this);
        final ImageButton btnPause = findViewById(R.id.activity_second__btn__pause);
        btnPause.setOnClickListener(this);
        final ImageButton btnNext = findViewById(R.id.activity_second__btn__next);
        btnNext.setOnClickListener(this);
        final ImageButton btnPrevious = findViewById(R.id.activity_second__btn__previous);
        btnPrevious.setOnClickListener(this);

        List<Song> songs = Utils.getListData();
        songPosition = getIntent().getExtras().getInt("position");

        imgSongPic.setImageResource(this.getResources().getIdentifier(songs.get(songPosition).getImageId(), "drawable", this.getPackageName()));
        tvTitle.setText(songs.get(songPosition).getTitle());
        tvComments.setText(songs.get(songPosition).getComments());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
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
}
