package org.buildmlearn.mconference.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.util.XMLParser;

public class Splash extends Activity implements Constants {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);

        final SharedPreferences sharedPref
                = getApplicationContext().getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Home.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    if(!sharedPref.getBoolean(PARSING_COMPLETE, false))
                        XMLParser.parse(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
