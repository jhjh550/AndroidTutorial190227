package com.example.a04_customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        ListView listView = findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inf = LayoutInflater.from(MainActivity.this);
                convertView = inf.inflate(R.layout.item_view, parent, false);
            }
            MyData myData = list.get(position);

            ImageView imageView = convertView.findViewById(R.id.itemIcon);
            TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
            TextView textViewDesc = convertView.findViewById(R.id.textViewDesc);

            textViewTitle.setText(myData.title);
            textViewDesc.setText(myData.desc);
            imageView.setImageResource(myData.imgIcon);

            return convertView;
        }
    }
}
