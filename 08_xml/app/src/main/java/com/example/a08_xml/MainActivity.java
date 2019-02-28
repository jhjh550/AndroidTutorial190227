package com.example.a08_xml;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a08_xml.model.WeatherData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<WeatherData> weatherList = new ArrayList<>();

    enum DataType { none, hourType, dayType, tempType, wfKorType }
    DataType type = DataType.none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        new MyParserTask().execute("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000");
    }
    // http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000
    // 0일 12시 9.0도 구름 조금
    // 0일 3시 9.0도 구름 조금
    class MyParserTask extends AsyncTask<String, Void, String>{
        ProgressDialog dlg;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dlg = new ProgressDialog(MainActivity.this);
            dlg.setTitle("로딩중");
            dlg.show();
        }

        @Override
        protected void onPostExecute(String s) {
            WeatherAdapter adapter = new WeatherAdapter();
            listView.setAdapter(adapter);
            dlg.dismiss();
        }

        @Override
        protected String doInBackground(String... strings) {
            String res = "";

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL(strings[0]);
                xpp.setInput(url.openStream(), "utf-8");
                int eventType = xpp.getEventType();
                WeatherData weatherData = null;
                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            String tag = xpp.getName();
                            switch (tag){
                                case "data":
                                    weatherData = new WeatherData();
                                    weatherList.add(weatherData);
                                    break;
                                case "hour":
                                    type = DataType.hourType;
                                    break;
                                case "day":
                                    type = DataType.dayType;
                                    break;
                                case "temp":
                                    type = DataType.tempType;
                                    break;
                                case "wfKor":
                                    type = DataType.wfKorType;
                                    break;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            switch (type){
                                case hourType:
                                    int hour = Integer.parseInt(xpp.getText());
                                    weatherData.setHour(hour);
                                    break;
                                case dayType:
                                    int day = Integer.parseInt(xpp.getText());
                                    weatherData.setDay(day);
                                    break;
                                case tempType:
                                    float temp = Float.parseFloat(xpp.getText());
                                    weatherData.setTemp(temp);
                                    break;
                                case wfKorType:
                                    weatherData.setWfKor(xpp.getText());
                                    break;
                            }
                            type = DataType.none;
                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("xml", "count : "+weatherList.size());
            return res;
        }
    }

    class WeatherAdapter extends BaseAdapter{

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
                LayoutInflater inf = LayoutInflater.from(MainActivity.this);
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
}
