package com.example.sensortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tx,ty,tz;
    TextView stepsTextView;
    TextView sensitiveTextView;

    SensorManager sensorManager;
    Sensor accelerometer;
    SeekBar seekBar;

    float previousY, currentY;
    int steps;
    int threshold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tx=(TextView) findViewById(R.id.textX);
        ty=(TextView) findViewById(R.id.textY);
        tz=(TextView) findViewById(R.id.textZ);

        stepsTextView=(TextView) findViewById(R.id.sensor);
        sensitiveTextView=(TextView)findViewById(R.id.sensitive);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        threshold=10;
        seekBar.setProgress(threshold);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        sensitiveTextView.setText(String.valueOf(threshold));

        previousY=currentY=steps=0;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            threshold=seekBar.getProgress();
            sensitiveTextView.setText(String.valueOf(threshold));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        currentY = y;
        if(Math.abs(currentY-previousY)>threshold){
            steps++;
            stepsTextView.setText(String.valueOf(steps));
        }
        tx.setText(String.valueOf(x));
        ty.setText(String.valueOf(y));
        tz.setText(String.valueOf(z));
        previousY=y;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void resetButton(View view) {
        steps=0;
        stepsTextView.setText(String.valueOf(steps));
    }
}