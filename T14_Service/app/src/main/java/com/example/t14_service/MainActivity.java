package com.example.t14_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
        findViewById(R.id.btnRandomNumber).setOnClickListener(this);
    }

    MyBoundService boundService;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder) service;
            boundService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            boundService = null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(conn);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRandomNumber){
            if(boundService != null){
                int num = boundService.getRandomNumber();
                Log.d("bound service", "random number : "+num);
            }
        }else if(v.getId() == R.id.btnStart){
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        }else{
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        }
    }
}
