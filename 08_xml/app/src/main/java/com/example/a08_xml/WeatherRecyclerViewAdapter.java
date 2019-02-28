package com.example.a08_xml;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a08_xml.model.WeatherData;

import java.util.ArrayList;

public class WeatherRecyclerViewAdapter
        extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherViewHolder> {
    ArrayList<WeatherData> weatherList;

    public WeatherRecyclerViewAdapter(ArrayList<WeatherData> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inf = LayoutInflater.from(viewGroup.getContext());
        View v = inf.inflate(R.layout.item_weather, viewGroup, false);
        return new WeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        weatherViewHolder.bindWeather(weatherList.get(i));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{
        WeatherData mWeatherData;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bindWeather(WeatherData data){
            mWeatherData = data;
        }
    }
}
