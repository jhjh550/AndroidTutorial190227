package com.example.podcastparser.model;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class PodcastItemManger {
    ArrayList<PodcastItem> podcastItemList;
    PodcastParserTask task;

    public void getPodcastData(){
        task = new PodcastParserTask();
        task.execute("http://pod.ssenhosting.com/rss/rrojia2/rrojia2.xml");
    }
    class PodcastParserTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            String res = "";
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new URL(strings[0]).openStream(), "utf-8");
                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            res += xpp.getName()+" ";
                            break;
                        case XmlPullParser.TEXT:
                            res += xpp.getText()+" ";
                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("xml", res);
            return null;
        }
    }
}
