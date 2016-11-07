package com.a2016reserch.sliit.insight.learning_module;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;

import com.a2016reserch.sliit.insight.R;
import com.a2016reserch.sliit.insight.learning_module.help_keypad.MainMenu_LearningModule;

public class SplashScreen_LearningModule extends Activity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen__learning_module);

        Thread background = new Thread() {
            public void run() {

                try {

                    // Thread will sleep for 5 seconds
                    sleep(3*1000);

                    // After 5 seconds redirect to another intent
                    Intent intent = new Intent(SplashScreen_LearningModule.this, MainMenu_LearningModule.class);
                    startActivity(intent);


                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }


}
