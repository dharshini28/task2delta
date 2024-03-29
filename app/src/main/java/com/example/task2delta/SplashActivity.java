package com.example.task2delta;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.task2delta.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_TIME_OUT= 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );
        new Handler ().postDelayed( new Runnable() {

            /*
             * Showing splash screen with p timer. This will be useful when you
             * wantbutton click in android to show case your app logo / company
             */

            @Override
            public void run(){

                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT );

    }
}