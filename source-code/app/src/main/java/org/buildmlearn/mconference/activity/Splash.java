package org.buildmlearn.mconference.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.util.XMLParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
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

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Log.d("XML", "Before XMLParser");
                    XMLParser.parse(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Home.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
