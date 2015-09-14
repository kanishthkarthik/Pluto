package com.quikr.pluto;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quikr.pluto.Backend.ReturningClass;

import java.util.Calendar;
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

                Intent intent = new Intent(SplashScreen.this, MainActivity.class);

                //Default dates
                Calendar calendar = Calendar.getInstance();

                String year = calendar.get(Calendar.YEAR) + "";
                String month = ((calendar.get(Calendar.MONTH)+1) > 9) ? "" : "0";
                month += (calendar.get(Calendar.MONTH)+1);
                String day = (calendar.get(Calendar.DAY_OF_MONTH) > 9) ? "" : "0";
                day += calendar.get(Calendar.DAY_OF_MONTH);
                String date = year + "-" + month + "-" + day;

                Log.d("*****",date);

                CommonResources.date=date;


                if(!ReturningClass.getDate(getApplicationContext()).equalsIgnoreCase(CommonResources.date))
                    GetToken.postMethod(getApplicationContext());

                //Token and ID generated
                //Set token ID and token
                CommonResources.token = ReturningClass.getToken(getApplicationContext());
                CommonResources.tokenId = ReturningClass.getTokenID(getApplicationContext());


                startActivity(intent);
                finish();

            }
        }, 400); //800 is the wait time to show next activity (That is, 0.4seconds)
    }

}