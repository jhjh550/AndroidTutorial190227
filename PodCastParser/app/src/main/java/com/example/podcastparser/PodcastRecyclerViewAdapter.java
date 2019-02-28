package com.example.podcastparser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.podcastparser.model.PodcastItem;

import java.util.ArrayList;

public class PodcastRecyclerViewAdapter extends RecyclerView.Adapter<PodcastRecyclerViewAdapter.PodcastViewHolder> {
    ArrayList<PodcastItem> mPodcastItems;

    public PodcastRecyclerViewAdapter(ArrayList<PodcastItem> podcastItems) {
        mPodcastItems = podcastItems;
    }

    @NonNull
    @Override
    public PodcastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inf = LayoutInflater.from(viewGroup.getContext());
        View v = inf.inflate(R.layout.item_view, viewGroup, false);
        return new PodcastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastViewHolder podcastViewHolder, int i) {
        podcastViewHolder.bindItem(mPodcastItems.get(i));
    }

    @Override
    public int getItemCount() {
        return mPodcastItems.size();
    }


    class PodcastViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewSummary, textViewPubDate, textViewDuration;
        PodcastItem mItem;

        public PodcastViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSummary= itemView.findViewById(R.id.textViewSummary);
            textViewPubDate = itemView.findViewById(R.id.textViewPubDate);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
        }
        public void bindItem(PodcastItem item){
            mItem = item;
            textViewTitle.setText(item.getTitle());
            textViewSummary.setText(item.getSummary());
            textViewPubDate.setText(item.getPubDate());
            textViewDuration.setText(item.getDuration());
        }
    }
}
