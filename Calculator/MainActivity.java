package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    Button plus, sub, mul, div, equal;
    Button clear;

    String number;

    int value;

    int PLUS=0;
    int SUB=1;
    int MUL=2;
    int DIV=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=(EditText)findViewById(R.id.editText);
        number="";

        plus=(Button)findViewById(R.id.add);
        sub=(Button)findViewById(R.id.sub);
        mul=(Button)findViewById(R.id.mul);
        div=(Button)findViewById(R.id.div);
        equal=(Button)findViewById(R.id.buttonOk);

        plus.setOnClickListener(mListener);
        sub.setOnClickListener(mListener);
        mul.setOnClickListener(mListener);
        div.setOnClickListener(mListener);
        equal.setOnClickListener(mListener);

        clear=(Button)findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number="";
                edit.setText("");
            }
        });
    }

    Button.OnClickListener mListener=new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.add:
                    number=edit.getText().toString();
                    edit.setText("");
                    value=PLUS;
                    Toast.makeText(MainActivity.this,"+",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sub:
                    number=edit.getText().toString();
                    edit.setText("");
                    value=SUB;
                    Toast.makeText(MainActivity.this,"-",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mul:
                    number=edit.getText().toString();
                    edit.setText("");
                    value=MUL;
                    Toast.makeText(MainActivity.this,"*",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.div:
                    number=edit.getText().toString();
                    edit.setText("");
                    value=DIV;
                    Toast.makeText(MainActivity.this,"/",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonOk:
                    if(value==PLUS){
                        edit.setText(""+(Double.parseDouble(number)+Double.parseDouble(edit.getText().toString())));
                    }
                    else if(value==SUB){
                        edit.setText(""+(Double.parseDouble(number)-Double.parseDouble(edit.getText().toString())));
                    }
                    else if(value==MUL){
                        edit.setText(""+(Double.parseDouble(number)*Double.parseDouble(edit.getText().toString())));
                    }
                    else if(value==DIV){
                        edit.setText(""+(Double.parseDouble(number)/Double.parseDouble(edit.getText().toString())));
                    }
                    number=edit.getText().toString();
                    break;
            }
        }
    };

    public void onClick (View v){
        switch (v.getId()){
            case R.id.n0 : edit.setText(edit.getText().toString()+0); break;
            case R.id.n1 : edit.setText(edit.getText().toString()+1); break;
            case R.id.n2 : edit.setText(edit.getText().toString()+2); break;
            case R.id.n3 : edit.setText(edit.getText().toString()+3); break;
            case R.id.n4 : edit.setText(edit.getText().toString()+4); break;
            case R.id.n5 : edit.setText(edit.getText().toString()+5); break;
            case R.id.n6 : edit.setText(edit.getText().toString()+6); break;
            case R.id.n7 : edit.setText(edit.getText().toString()+7); break;
            case R.id.n8 : edit.setText(edit.getText().toString()+8); break;
            case R.id.n9 : edit.setText(edit.getText().toString()+9); break;

        }
    }

}