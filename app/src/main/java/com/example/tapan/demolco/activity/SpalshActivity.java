package com.example.tapan.demolco.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.tapan.demolco.MainActivity;
import com.example.tapan.demolco.R;

public class SpalshActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                initialNavigate();
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


    }

    private void initialNavigate() {
        Intent i = new Intent(SpalshActivity.this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
    }



}
