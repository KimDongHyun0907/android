package com.example.graphictest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    public MyView(Context context, AttributeSet attrs) {
        super(context);
        setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
        canvas.drawLine(100,100,700,100,paint);
        canvas.drawRect(100,300,700,700,paint);
        canvas.drawCircle(300,1200,200,paint);
        paint.setTextSize(80);
        canvas.drawText("This is a test",100,900,paint);
        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.cat);
        canvas.drawBitmap(b,100,1500,null);
    }
}
