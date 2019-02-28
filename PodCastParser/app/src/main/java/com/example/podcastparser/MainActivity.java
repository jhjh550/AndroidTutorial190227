package com.example.podcastparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.podcastparser.model.PodcastItemManger;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PodcastItemManger manager = new PodcastItemManger();
        manager.getPodcastData();

    }
}
