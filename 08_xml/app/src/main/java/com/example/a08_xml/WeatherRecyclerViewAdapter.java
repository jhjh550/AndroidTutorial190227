package com.example.a08_xml;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a08_xml.model.WeatherData;

import java.util.ArrayList;

public class WeatherRecyclerViewAdapter
        extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherViewHolder> {
    ArrayList<WeatherData> weatherList;
    Context context;

    public WeatherRecyclerViewAdapter(Context context, ArrayList<WeatherData> weatherList) {
        this.weatherList = weatherList;
        this.context = context;
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
        TextView textViewWfKor, textViewTemp, textViewDate;
        ImageView imageView;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWfKor = itemView.findViewById(R.id.textViewWfKor);
            textViewTemp = itemView.findViewById(R.id.textViewTemp);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            imageView = itemView.findViewById(R.id.imageViewIcon);

        }
        public void bindWeather(WeatherData data){
            mWeatherData = data;

            textViewWfKor.setText(data.getWfKor());
            textViewTemp.setText(data.getTemp()+" 'c");

            textViewDate.setText(data.getDay()+"일 "+data.getHour()+"시");
            int res = R.mipmap.ic_launcher;
            if(data.getWfKor().contains("구름")){
                res = R.drawable.ic_cloud;
            }else if(data.getWfKor().contains("맑음")){
                res = R.drawable.ic_wb_sunny;
            }
            imageView.setImageResource(res);
        }
    }
}
