package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediaplayer.data.Song;
import com.example.mediaplayer.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Song> songs = Utils.getListData();

        final ListView listView = findViewById(R.id.activity_main__list_view__data);
        listView.setAdapter(new MyListAdapter(this, R.layout.custom_adapter, songs));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Position " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
