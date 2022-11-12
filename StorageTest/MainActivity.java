package com.example.storagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView name;
    EditText value;
    String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(TextView) findViewById(R.id.TextView01);
        value=(EditText) findViewById(R.id.EditText01);

        SharedPreferences settings = getSharedPreferences("myPref",0);
        imageName = settings.getString("imageName","");
        value.setText(imageName);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences("myPref",0);
        SharedPreferences.Editor editor= settings.edit();
        imageName = value.getText().toString();
        editor.putString("imageName",imageName);
        editor.commit();
    }
}