package com.example.musicservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final int PERMISSION_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS,
//                    Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
//        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode==PERMISSION_REQUEST){
//            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                Log.i("permission","granted");
//            }else{
//                Log.i("permission","Not granted");
//            }
//        }
//    }

    public void onClickStart(View view) {
        Intent intent = new Intent(getBaseContext(), MyService.class);
        startService(intent);
    }

    public void onClickStop(View view) {
        Intent intent = new Intent(getBaseContext(), MyService.class);
        stopService(intent);
    }
}