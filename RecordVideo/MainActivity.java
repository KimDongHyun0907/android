package com.example.recordvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    Button btnRecord;
    Button btnPlay;

    SurfaceView surfaceViewForRecord;
    SurfaceHolder surfaceHolderforRecord;
    Camera camera;
    SurfaceView getSurfaceViewForPlay;
    SurfaceHolder surfaceHolderforPlay;

    boolean isRecording=false;
    boolean isPlaying=false;
    boolean hasvideo=false;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecord = (Button) findViewById(R.id.btn_record);
        btnPlay = (Button) findViewById(R.id.btn_play);
        surfaceViewForRecord = (SurfaceView) findViewById(R.id.recordView);
        getSurfaceViewForPlay = (SurfaceView) findViewById(R.id.playView);

        File file = new File(getExternalFilesDir(null), "record.mp4");
        mPath=file.getAbsolutePath();

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},10);

    }

    public void btnRecord(View view) {
        hasvideo = true;

        if(isRecording==true){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder=null;

            camera.stopPreview();
            surfaceViewForRecord=null;
            isRecording=false;
            btnRecord.setText("녹화 시작");

        }else{
            mediaRecorder = new MediaRecorder();
            camera = Camera.open();
            camera.setDisplayOrientation(90);

            surfaceHolderforRecord = surfaceViewForRecord.getHolder();
            surfaceHolderforRecord.addCallback(this);

            camera.unlock();
            mediaRecorder.setCamera(camera);
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
            mediaRecorder.setOrientationHint(90);
            mediaRecorder.setOutputFile(mPath);

            mediaRecorder.setPreviewDisplay(surfaceHolderforRecord.getSurface());

            try{
                mediaRecorder.prepare();
            }catch (IOException e) {
                e.printStackTrace();
            }

            mediaRecorder.start();
            btnRecord.setText("녹화 중지");
            isRecording = true;
        }
    }

    public void btnPlay(View view) {
        if(hasvideo==true){
            if(isPlaying==false){
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                        isPlaying=false;
                        btnPlay.setText("재생 시작");
                    }
                });

                surfaceHolderforPlay=getSurfaceViewForPlay.getHolder();
                try {
                    mediaPlayer.setDataSource(mPath);
                    mediaPlayer.setDisplay(surfaceHolderforPlay);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    isPlaying=true;
                    btnPlay.setText("재생 중지");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer=null;
                isPlaying=false;
                btnPlay.setText("재생 시작");
            }
        }

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}