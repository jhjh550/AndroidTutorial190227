package com.example.t13_mediaplayer;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PERMISSION_REQ_CODE = 100;

    MediaPlayer mp = null;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    if(mp != null){
                        mp.seekTo(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setupPermission();

        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
    }



    private void setupPermission(){
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Permission to read external storage is required for this app");
                builder.setTitle("Permission required");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                PERMISSION_REQ_CODE);
                    }
                });
                AlertDialog dlg = builder.create();
                dlg.show();
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQ_CODE);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQ_CODE){
            if(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.i("Permission", "granted");
            }else{
                Log.i("Permission", "not granted");
            }
        }
    }

    private String getPath(int id){
        if(id == R.id.btnPlay){
            return "http://www.hochmuth.com/mp3/Haydn_Cello_Concerto_D-1.mp3";
        }else{
            return Environment.getExternalStorageDirectory()+"/Kalimba.mp3";
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPlay || v.getId() == R.id.btnPlayFile){
            mp = new MediaPlayer();
            try {
                mp.setDataSource( getPath( v.getId() ));
                mp.prepare();
                mp.start();
                seekBar.setMax(mp.getDuration());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mp != null){
                            seekBar.setProgress(mp.getCurrentPosition());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(v.getId() == R.id.btnStop){
            if(mp != null){
                mp.stop();
                mp.release();
                mp = null;
            }
        }
    }
}
