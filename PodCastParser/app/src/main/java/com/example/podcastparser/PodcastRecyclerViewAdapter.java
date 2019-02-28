package com.example.podcastparser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.podcastparser.model.PodcastItem;

import java.util.ArrayList;

public class PodcastRecyclerViewAdapter extends RecyclerView.Adapter<PodcastRecyclerViewAdapter.PocastViewHolder> {
    ArrayList<PodcastItem> mPodcastItems;

    public PodcastRecyclerViewAdapter(ArrayList<PodcastItem> podcastItems) {
        mPodcastItems = podcastItems;
    }

    @NonNull
    @Override
    public PocastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PocastViewHolder pocastViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class PocastViewHolder extends RecyclerView.ViewHolder{

        public PocastViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
