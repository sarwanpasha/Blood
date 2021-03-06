package com.example.root.giveblood;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.root.com.example.root.Registration.Login;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

//                Intent i = new Intent(SplashScreen.this,HomeActivity.class);
//                startActivity(i);

//                Intent i = new Intent(SplashScreen.this,Select_Donor_Or_Receptor.class);
//                startActivity(i);

                Intent i = new Intent(SplashScreen.this,Select_Donor_Or_Receptor_3.class);
                startActivity(i);

//                Intent i = new Intent(SplashScreen.this,Login.class);
//                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}
