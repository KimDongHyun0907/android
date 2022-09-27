package com.example.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView myView=new MyView(this);
        setContentView(myView);
    }
}

class MyView extends View{
    float x,y;

    private Paint paint = new Paint();
    private Path path = new Path();

    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(float)event.getX();
        y=(float)event.getY();

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            path.moveTo(x,y);
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            path.lineTo(x,y);
        }
        if(event.getAction()==MotionEvent.ACTION_UP){

        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }
}

