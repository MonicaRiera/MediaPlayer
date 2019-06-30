package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mediaplayer.utils.Utils;


public class MainActivity extends AppCompatActivity {

    private PlayerFragment player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.activity_main__list_view__data);
        listView.setAdapter(new MyListAdapter(this, R.layout.custom_adapter, Utils.getListData()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                player.updateInfo(position);
                player.generateIntent(position);
                Utils.generateNotification(MainActivity.this, position);
            }
        });

        this.player = (PlayerFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main__fr__player);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.isFinishing()) {
        }
    }
}
