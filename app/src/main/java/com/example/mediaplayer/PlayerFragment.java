package com.example.mediaplayer;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment implements View.OnClickListener {

    private static int songPosition;
    private List<Song> songs;


    public PlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_player, container, false);
        ImageButton btnPlay = root.findViewById(R.id.fragment_player__btn__play);
        btnPlay.setOnClickListener(this);
        ImageButton btnPause = root.findViewById(R.id.fragment_player__btn__pause);
        btnPause.setOnClickListener(this);
        ImageButton btnPrevious = root.findViewById(R.id.fragment_player__btn__previous);
        btnPrevious.setOnClickListener(this);
        ImageButton btnNext = root.findViewById(R.id.fragment_player__btn__next);
        btnNext.setOnClickListener(this);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        songs = Utils.getListData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_player__btn__play:
                generateIntent(songPosition);
                Utils.generateNotification(getContext(), songPosition);
                break;
            case R.id.fragment_player__btn__pause:
                generateIntent(-1);
                break;
            case R.id.fragment_player__btn__next:
                if (songPosition < songs.size()-1) {
                    songPosition += 1;
                    generateIntent(songPosition);
                } else {
                    songPosition = 0;
                    generateIntent(songPosition);
                }
                Utils.generateNotification(getContext(), songPosition);
                break;
            case R.id.fragment_player__btn__previous:
                if (songPosition > 0) {
                    songPosition -= 1;
                    generateIntent(songPosition);
                } else {
                    songPosition = songs.size()-1;
                    generateIntent(songPosition);
                }
                Utils.generateNotification(getContext(), songPosition);
                break;
            default:
                break;
        }
    }

    public void updateInfo(int songposition) {
        this.songPosition = songposition;
    }

    public void generateIntent(int position) {
        if (position != -1) {
            this.songPosition = position;
        }
        Intent intent = new Intent(getContext(), MyMediaService.class);
        intent.putExtra("position", position);
        getContext().startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
