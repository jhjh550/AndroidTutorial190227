package com.example.a07_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        MyTask task = new MyTask();
        task.execute(30);
    }

    class MyTask extends AsyncTask<Integer, Integer, String> {


        @Override
        protected void onPostExecute(String s) {
            btnStart.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            btnStart.setText("count : "+values[0]);
        }

        @Override
        protected String doInBackground(Integer... values) {
            for(int i=values[0]; i<100; i++){
                Log.d("MyTask", "count : "+i);
                publishProgress(i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "count done!!";
        }
    }
}
