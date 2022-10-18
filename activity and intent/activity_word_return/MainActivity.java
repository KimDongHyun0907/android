package com.example.activityresulttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    static final int GET_STRING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text2);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(intent, GET_STRING);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            text.setText(data.getStringExtra("INPUT_TEXT"));
    }

}

//    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
//        new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if(result.getResultCode()==RESULT_OK){
//                    Intent data = result.getData();
//                    text.setText(data.getStringExtra("INPUT_TEXT"));
//                }
//            }
//    });
//    public void onClick(View view) {
//        Intent intent = new Intent(MainActivity.this,SubActivity.class);
//        //startActivityForResult(intent,GET_STRING);
//        launcher.launch(intent);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==RESULT_OK)
//            text.setText(data.getStringExtra("INPUT_TEXT"));
//    }