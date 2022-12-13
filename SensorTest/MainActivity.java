package com.example.sensortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String report = "";
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        report += "전체 센서수 : " + sensorList.size() + "\n";
        for (Sensor s: sensorList){
            report += "Name: "+s.getName() + "\n" + "resolution: " + s.getResolution() + "\n";
        }
        textView = (TextView)findViewById(R.id.text);
        textView.setText(report);
    }
}