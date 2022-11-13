package com.example.storagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText value;
    String FILENAME = "text.txt";
    //String FILENAME = "text2.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value=(EditText) findViewById(R.id.EditText01);

        Button readButton = (Button) findViewById(R.id.read);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File(getExternalFilesDir(null), FILENAME);
                    //FileInputStream fis = new FileInputStream(file);
                    FileInputStream fis = openFileInput(FILENAME);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    value.setText(new String(buffer));
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button writeButton = (Button) findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
//                    String state = Environment.getExternalStorageState();
//                    if(state.equals(Environment.MEDIA_MOUNTED)){
//                        File file = new File(getExternalFilesDir(null), FILENAME);
//                        FileOutputStream fos = new FileOutputStream(file);
//                        fos.write(value.getText().toString().getBytes());
//                        fos.close();
//                    }
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(value.getText().toString().getBytes());
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}