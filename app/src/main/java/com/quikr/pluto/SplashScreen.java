package com.quikr.pluto;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends ActionBarActivity {

    TextView face_tv, focusAcademy_tv;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Hide the action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        focusAcademy_tv = (TextView) findViewById(R.id.quikr_tv);
        face_tv = (TextView) findViewById(R.id.pluto_tv);
        relativeLayout = (RelativeLayout) findViewById(R.id.splashScreen_rl);

        Typeface robotoF= Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        face_tv.setTypeface(robotoF);
        focusAcademy_tv.setTypeface(robotoF);


        //final boolean login = Login.checkLogin(this);

        //call home screen after 800 milliseconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent;
                //if(login)
                intent = new Intent(SplashScreen.this, MainActivity.class);
                //else
                //  intent = new Intent(SplashScreen.this, LoginActivity.class);

                startActivity(intent);
                finish();

            }
        }, 800); //800 is the wait time to show next activity (That is, 0.8seconds)
    }

}