package com.example.a06_thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_COUNT = 1;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MY_COUNT){
                btnStart.setText("count "+msg.arg1);
            }
        }
    };
    class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0; i<100; i++){
                Log.d("thread", "count : "+i);
                Message msg = new Message();
                msg.what = MY_COUNT;
                msg.arg1 = i;
                mHandler.sendMessage(msg);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyThread th = new MyThread();
        th.start();
    }
}
