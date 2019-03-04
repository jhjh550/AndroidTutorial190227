package com.example.t11_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("mySettings", 0);
        String name = preferences.getString("name", "no name");
        Log.d("pref", "name : "+name);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences preferences = getSharedPreferences("mySettings", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", "hello world");

        editor.commit();
    }
}
