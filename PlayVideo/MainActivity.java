package com.example.capturetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 10;

    private String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    boolean isVideoPlaying = false;
    VideoView videoView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, permissions,REQUEST_PERMISSION);
        videoView = (VideoView) findViewById(R.id.videoview);
        button=(Button) findViewById(R.id.playVideo);
    }

    public void playVideo(View view) {
        if(isVideoPlaying==false){
            button.setText("비디오 중지");
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));
            videoView.start();
        }else{
            button.setText("비디오 재생");
            videoView.pause();
        }
        isVideoPlaying=!isVideoPlaying;
    }
}