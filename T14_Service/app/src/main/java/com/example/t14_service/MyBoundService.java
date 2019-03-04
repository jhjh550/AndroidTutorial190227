package com.example.t14_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyBoundService extends Service {
    public class MyBinder extends Binder{
        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }
    MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Random random = new Random();
    public int getRandomNumber(){
        return random.nextInt(100);
    }
}
