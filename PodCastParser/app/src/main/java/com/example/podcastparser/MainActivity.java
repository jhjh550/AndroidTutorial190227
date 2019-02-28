package com.example.podcastparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.podcastparser.model.PodcastItem;
import com.example.podcastparser.model.PodcastItemManger;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    PodcastItemManger manager;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        manager = new PodcastItemManger();
        manager.getPodcastData();
        manager.setOnPodcastFinishListener(new PodcastItemManger.PodcastFinishListener() {
            @Override
            public void onFinish() {
                setRecyclerView();
            }
        });

    }

    private void setRecyclerView(){
        ArrayList<PodcastItem> list = manager.getPodcastItemList();
        PodcastRecyclerViewAdapter adapter = new PodcastRecyclerViewAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
