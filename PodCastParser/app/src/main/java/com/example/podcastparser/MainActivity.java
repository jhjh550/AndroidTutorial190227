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
        textView = findViewById(R.id.textView);

        final PodcastItemManger manager = new PodcastItemManger();
        manager.getPodcastData();
        manager.setOnPodcastFinishListener(new PodcastItemManger.PodcastFinishListener() {
            @Override
            public void onFinish() {
                String temp = manager.getPocastTags();
                textView.setText(temp);
            }
        });

    }
}
