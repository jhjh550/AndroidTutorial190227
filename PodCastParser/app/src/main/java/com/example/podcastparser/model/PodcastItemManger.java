package com.example.podcastparser.model;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class PodcastItemManger {
    public interface PodcastFinishListener{
        void onFinish();
    }
    PodcastFinishListener mPodcastFinishListener;
    public void setOnPodcastFinishListener(PodcastFinishListener listener){
        mPodcastFinishListener = listener;
    }

    ArrayList<PodcastItem> podcastItemList = new ArrayList<>();
    PodcastParserTask task;

    public ArrayList<PodcastItem> getPodcastItemList() {
        return podcastItemList;
    }

    public void getPodcastData(){
        task = new PodcastParserTask();
        task.execute("http://pod.ssenhosting.com/rss/rrojia2/rrojia2.xml");
    }

    enum DataType {none, titleType, summaryType, guidType, pubDateType, durationType}
    DataType dataType = DataType.none;

    class PodcastParserTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            if(mPodcastFinishListener != null){
                mPodcastFinishListener.onFinish();
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new URL(strings[0]).openStream(), "utf-8");
                int eventType = xpp.getEventType();
                PodcastItem item = null;
                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            String tag = xpp.getName();
                            switch (tag){
                                case "item":
                                    item = new PodcastItem();
                                    podcastItemList.add(item);
                                    break;
                                case "title":
                                    dataType = DataType.titleType;
                                    break;
                                case "itunes:summary":
                                    dataType = DataType.summaryType;
                                    break;
                                case "guid":
                                    dataType = DataType.guidType;
                                    break;
                                case "pubDate":
                                    dataType = DataType.pubDateType;
                                    break;
                                case "itunes:duration":
                                    dataType = DataType.durationType;
                                    break;
                            }

                            break;
                        case XmlPullParser.TEXT:
                            switch (dataType){
                                case titleType:
                                    if(item != null) {
                                        item.setTitle(xpp.getText());
                                    }
                                    break;
                                case summaryType:
                                    if(item != null) {
                                        item.setSummary(xpp.getText());
                                    }
                                    break;
                                case guidType:
                                    item.setGuid(xpp.getText());
                                    break;
                                case pubDateType:
                                    item.setPubDate(xpp.getText());
                                    break;
                                case durationType:
                                    item.setDuration(xpp.getText());
                                    break;
                            }
                            dataType = DataType.none;

                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("podcast", podcastItemList.size()+"");
            return null;
        }
    }
}
