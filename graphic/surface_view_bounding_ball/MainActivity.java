package com.example.graphictest;

import androidx.annotation.NonNull;
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
import android.view.Display;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
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
        setContentView(new MysurfaceView(getApplicationContext()));
    }
}

class Ball{
    int x,y,xInc,yInc;
    int diameter;
    int WIDTH, HEIGHT;
    int color;

    Ball(int d, int width, int height){
        this.diameter=d;
        this.WIDTH=width;
        this.HEIGHT=height;

        x=(int)(Math.random()*(this.WIDTH-d)+3);
        y=(int)(Math.random()*(this.HEIGHT-d)+3);

        xInc=(int)(Math.random()*30+1);
        yInc=(int)(Math.random()*30+1);

        this.color=Color.RED;
    }

    void paint(Canvas g){
        Paint paint=new Paint();

        if(x < diameter || x > (WIDTH-diameter))
            xInc=-xInc;

        if(y < diameter || y > (HEIGHT-diameter))
            yInc=-yInc;

        x+=xInc;
        y+=yInc;
        paint.setColor(this.color);
        g.drawCircle(x,y,diameter,paint);
    }

}
class MysurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    Ball basket[]=new Ball[10];
    MyThread thread;

    public MysurfaceView(Context context) {
        super(context);
        SurfaceHolder holder=getHolder();
        holder.addCallback(this);
        thread=new MyThread(holder);
        Display display=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width=display.getWidth();
        int height=display.getHeight();
        for(int i=0;i<10; i++){
            basket[i]=new Ball(20,width,height);
        }
        //thread=new MyThread(holder);
    }

    public MyThread getThread(){
        return thread;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
    class MyThread extends Thread{
        boolean mRun=false;
        SurfaceHolder mSurfaceHolder;

        MyThread(SurfaceHolder surfaceHolder){
            this.mSurfaceHolder=surfaceHolder;
        }

        @Override
        public void run() {
            super.run();
            while(mRun){
                Canvas c=null;
                try{
                    c=mSurfaceHolder.lockCanvas(null);
                    c.drawColor(Color.BLACK);
                    synchronized (mSurfaceHolder){
                        for(Ball b: basket){
                            b.paint(c);
                        }
                    }
                } finally {
                    if(c!=null){
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        void setRunning(boolean b) {
            mRun=b;
        }
    }
}


