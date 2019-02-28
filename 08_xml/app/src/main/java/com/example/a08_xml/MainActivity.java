package com.example.a08_xml;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;

import com.example.a08_xml.model.WeatherData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ArrayList<WeatherData> weatherList = new ArrayList<>();

    enum DataType { none, hourType, dayType, tempType, wfKorType }
    DataType type = DataType.none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        new MyParserTask().execute("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000");
    }
    // http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000
    // 0일 12시 9.0도 구름 조금
    // 0일 3시 9.0도 구름 조금
    class MyParserTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
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
                WeatherData weatherData;
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

                            }
                            break;
                        case XmlPullParser.TEXT:
                            switch (type){
                                case hourType:

                            }
                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }
}
