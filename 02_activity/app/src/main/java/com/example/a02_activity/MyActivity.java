package com.example.a02_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Intent intent = getIntent();
        String str = intent.getStringExtra("myValue");
        int intValue = intent.getIntExtra("intValue", 0);

        Toast.makeText(this, str+intValue, Toast.LENGTH_SHORT).show();
        Button btnRes = findViewById(R.id.btnRes);
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                resIntent.putExtra("myResult", "MY RESULT!");
                setResult(RESULT_OK, resIntent);
                finish();
            }
        });
    }
}
