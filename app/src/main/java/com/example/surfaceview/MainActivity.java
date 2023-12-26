package com.example.surfaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawSurfaceView ds;
    Thread thread;
    boolean userAskBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ds = new DrawSurfaceView(this);
        thread = new Thread(ds);
        thread.start();

        ViewGroup.LayoutParams layoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(ds, layoutParams);

    }

    @Override
    public void finish() {
        super.finish();
        userAskBack = true;
        ds.threadRunning = false;
        while (true){
            try {
                thread.join(); //הורס אותו
            }
            catch (Exception e){
                e.printStackTrace();
            }
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ds != null){
            ds.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("data", "Pause");
        if(userAskBack){
            Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show(); // למשתמש על המסך
            Log.d("data", "user ask back");
        }
        else if(ds != null){
            ds.pause();
        }
    }
}