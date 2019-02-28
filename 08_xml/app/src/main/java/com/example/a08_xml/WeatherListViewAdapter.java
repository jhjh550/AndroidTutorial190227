package com.example.a08_xml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a08_xml.model.WeatherData;

import java.util.ArrayList;

public class WeatherListViewAdapter extends BaseAdapter {
    ArrayList<WeatherData> weatherList;
    Context context;

    public WeatherListViewAdapter(ArrayList<WeatherData> weatherList, Context context) {
        this.weatherList = weatherList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return weatherList.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inf = LayoutInflater.from(context);
            convertView = inf.inflate(R.layout.item_weather, parent, false);
        }
        WeatherData data = weatherList.get(position);
        TextView textViewWfKor = convertView.findViewById(R.id.textViewWfKor);
        TextView textViewTemp = convertView.findViewById(R.id.textViewTemp);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        ImageView imageView = convertView.findViewById(R.id.imageViewIcon);

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
        return convertView;
    }
}
