package com.example.nuc.wordremember.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.nuc.wordremember.R;

public class SplashActivity extends AppCompatActivity {


    private Handler handler;
    private boolean isStartMain = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {
                StartMainactivity();
            }
        },2000);

    }

    private void StartMainactivity() {
        if(!isStartMain){
            isStartMain = true;
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        StartMainactivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
