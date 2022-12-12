package com.example.mediatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;

    boolean mStartRecording = false;
    boolean mStartPlaying = false;

    Button recordButton;
    Button playButton;

    private static final int REQUEST_AUDIO_PERMISSION = 200;
    private static String filename = "recorded.mp4";

    private String[] permissions = {Manifest.permission.RECORD_AUDIO,
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(getExternalFilesDir(null), filename);
        //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),filename);
        filename = file.getAbsolutePath();

        ActivityCompat.requestPermissions(this, permissions,REQUEST_AUDIO_PERMISSION);

        recordButton = (Button) findViewById(R.id.buttonRecord);
        playButton = (Button) findViewById(R.id.buttonPlay);
    }

    public void onClickRecord(View view) {
        if(mStartRecording == false){
            recordButton.setText("녹음 중지");
        } else{
            recordButton.setText("녹음 시작");
        }
        mStartRecording = !mStartRecording;
        onRecord(mStartRecording);
    }

    private void onRecord(boolean recording) {
        if(recording == true){
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            mediaRecorder.setOutputFile(filename);

            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaRecorder.start();
            Toast.makeText(this, "녹음이 시작되었습니다.", Toast.LENGTH_LONG).show();
        }else{
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder=null;
            Toast.makeText(this, "녹음이 중지되었습니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickPlay(View view) {
        if(mStartPlaying==false){
            playButton.setText("재생 중지");
        }else{
            playButton.setText("재생 시작");
        }
        mStartPlaying = !mStartPlaying;
        onplay(mStartPlaying);
    }

    void onplay(boolean playing){
        if (playing == true) {
            mediaPlayer=new MediaPlayer();
            try {
                mediaPlayer.setDataSource(filename);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            Toast.makeText(this, "재생이 시작되었습니다.", Toast.LENGTH_LONG).show();
        } else{
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "재생이 중지되었습니다.", Toast.LENGTH_LONG).show();
        }
    }
}