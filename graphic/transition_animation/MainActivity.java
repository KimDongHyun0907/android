package com.example.graphictest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button fadeButton;
    Button slideButton;
    Button explodeButton;
    ImageView imageView;
    boolean visible=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyView view=new MyView(this);
        //setContentView(view);
        setContentView(R.layout.activity_main);

        linearLayout=(LinearLayout) findViewById(R.id.layout);
        fadeButton=(Button) findViewById(R.id.fade);
        slideButton=(Button) findViewById(R.id.slide);
        explodeButton=(Button) findViewById(R.id.explode);
        imageView=(ImageView) findViewById(R.id.imageview);

        fadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transition transition=new Fade();
                transition.setDuration(2000);
                TransitionManager.beginDelayedTransition(linearLayout,new Fade());
                visible=!visible;
                imageView.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }
        });

        slideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transition transition=new Slide(Gravity.START);
                transition.setDuration(2000);
                TransitionManager.beginDelayedTransition(linearLayout,new Slide());
                visible=!visible;
                imageView.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }
        });

        explodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transition transition=new Explode();
                transition.setDuration(2000);
                TransitionManager.beginDelayedTransition(linearLayout,new Explode());
                visible=!visible;
                imageView.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }
}