package com.example.a04_customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        String title;
        String desc;
        int imgIcon;

        public MyData(String title, String desc, int imgIcon) {
            this.title = title;
            this.desc = desc;
            this.imgIcon = imgIcon;
        }
    }

    ArrayList<MyData> list = new ArrayList<>();
    private void setMyData(){
        for(int i=0; i<100; i++){
            int icon = i%2==0 ? R.mipmap.ic_launcher : R.mipmap.ic_launcher_round;
            MyData myData = new MyData("title"+i, "desc"+i, icon);
            list.add(myData);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMyData();
    }
}
