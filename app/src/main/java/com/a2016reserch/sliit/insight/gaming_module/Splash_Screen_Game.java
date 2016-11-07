package com.a2016reserch.sliit.insight.gaming_module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a2016reserch.sliit.insight.R;

public class Splash_Screen_Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen__game);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 3 seconds
                    sleep(3*1000);

                    // After 3 seconds redirect to another intent
                    Intent intent = new Intent(Splash_Screen_Game.this, MainMenu_GamingModule.class);
                    startActivity(intent);


                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }

}
