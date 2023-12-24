package com.example.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.system.SystemCleaner;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawSurfaceView extends SurfaceView implements Runnable {

    float deltax = 10;
    float deltay = 10;
    Context context;
    SurfaceHolder holder;
    boolean threadRunning = true;
    boolean isRunning = true;

    Bitmap bitmap;
    float coordx = 100;
    float coordy = 100;

    public DrawSurfaceView(Context context) {

        super(context);
        this.context = context;
        holder = getHolder();
    }


    @Override
    public void run() {

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bamba_nugat);
        bitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, false);

        while (threadRunning) { // כל המשחק ממשיך לפעול

            if (isRunning) { //כשמישהו מנצח.מפסיד
                if (!holder.getSurface().isValid())
                    continue;

                Canvas c = null;
                try {
                    c = this.getHolder().lockCanvas();
                    synchronized (this.getHolder()) {
                        c.drawRGB(100, 200, 135);
                        c.drawBitmap(bitmap, coordx, coordy, null);
                        coordx = coordx + deltax;
                        coordy = coordy + deltay;
                        if (coordx < 0 || coordx > this.getWidth() - bitmap.getWidth())
                            deltax = -deltax;
                        if (coordy < 0 || coordy > this.getHeight() - bitmap.getHeight())
                            deltay = -deltay;

                        //כאן יהיהה המשחק

                        SystemClock.sleep(10);
                    }

                } catch (Exception e) {
                    Log.d("WHY WHY", "run: " + e.getMessage());

                } finally {
                    if (c != null) {
                        this.getHolder().unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }

    public void pause() {
        isRunning = false;
    }

    public void resume() {
        isRunning = true;
    }

    public void destroy(){
        isRunning = false;
        ((MainActivity)context).finish();
    }
}



