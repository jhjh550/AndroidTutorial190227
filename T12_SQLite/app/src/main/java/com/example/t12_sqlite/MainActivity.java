package com.example.t12_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestDBHandler dbHandler = new TestDBHandler(this);
        dbHandler.insert("kim", 10, "seoul");
        dbHandler.insert("lee", 11, "인천");
        dbHandler.insert("park", 12, "busan");
        dbHandler.update("kim", 20);
        String res = dbHandler.selectAll();
        Log.i("SQLite", res);
    }
}
