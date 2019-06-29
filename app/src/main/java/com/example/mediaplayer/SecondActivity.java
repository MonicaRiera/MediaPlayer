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

public class SecondActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private int songPosition;
    private PlayerFragment player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        List<Song> songs = Utils.getListData();
        songPosition = getIntent().getExtras().getInt("position");

        ImageView imgSongPic = findViewById(R.id.activity_second__img__song_image);
        imgSongPic.setImageResource(this.getResources().getIdentifier(songs.get(songPosition).getImageId(), "drawable", this.getPackageName()));
        TextView tvTitle = findViewById(R.id.activity_second__tv__song_title);
        tvTitle.setText(songs.get(songPosition).getTitle());
        TextView tvComments = findViewById(R.id.activity_second__tv__comments);
        tvComments.setText(songs.get(songPosition).getComments());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_second__fr__map);
        mapFragment.getMapAsync(this);

        this.player = (PlayerFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main__fr__player);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng location = Utils.getListData().get(songPosition).getLocation();
        map.addMarker(new MarkerOptions().position(location));
        map.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

}
