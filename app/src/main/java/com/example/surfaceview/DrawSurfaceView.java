package com.example.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawSurfaceView extends SurfaceView implements Runnable {

    Context context;
    SurfaceHolder holder;
    boolean threadRunning = true;
    boolean isRunning = true;
    public DrawSurfaceView(Context context){

        super(context);
        this.context = context;
        holder = getHolder();
    }


    @Override
    public void run() {

        while (threadRunning) // כל המשחק ממשיך לפעול{

            if(isRunning)// כשמישהו מנצח.מפסיד {
                if(!holder.getSurface().isValid())
                    continue;

                Canvas c = null;
                try {
                    c = this.getHolder();
                }
                catch (Exception e){

                }
                finally {

                }
            }
        }


    }
}
