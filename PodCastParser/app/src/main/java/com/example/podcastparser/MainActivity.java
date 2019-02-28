package com.example.podcastparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.podcastparser.model.PodcastItemManger;

public class MainActivity extends AppCompatActivity {
    PodcastItemManger manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}
