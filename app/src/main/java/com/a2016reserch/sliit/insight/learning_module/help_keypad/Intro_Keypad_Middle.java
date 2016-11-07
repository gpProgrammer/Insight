package com.a2016reserch.sliit.insight.learning_module.help_keypad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.a2016reserch.sliit.insight.R;

import java.util.Locale;

public class Intro_Keypad_Middle extends Activity {

    Button btntop,btnmiddle,btnbottom;
    TextToSpeech tts;
    boolean IsTouched=false;

    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__keypad__middle);

        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();

        mGestureDetector = new GestureDetector(this, android_gesture_detector);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btntop=(Button)findViewById(R.id.button7);
        btnmiddle=(Button)findViewById(R.id.button11);
        btnbottom=(Button)findViewById(R.id.button6);

        btnmiddle.setBackgroundColor(Color.parseColor("#f9e200"));
        btntop.setEnabled(false);
        btnmiddle.setEnabled(true);
        btnbottom.setEnabled(false);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    int result = tts.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization Failed!");
                }


            }
        });

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    speakWords("Hi guys...");
                    sleep(3000);
                    speakWords("Let's learn about the Middle area of screen");
                    sleep(3000);
                    speakWords("Try to touch on middle  area of the mobile screen. If you touch correctly, you will feel a vibration to your hand");
                    sleep(3000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();


        btnmiddle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(!IsTouched) {
                    vibrator.vibrate(300);
                    speakWords("You are touching" + btnmiddle.getText().toString() +  "area of the screen."+"Middle area also divided in to two main parts. Swipe right to left on the screen to identify those parts");


                    btnmiddle.setBackgroundColor(Color.RED);

                    return IsTouched;
                }
                else
                {
                    btnmiddle.setBackgroundColor(Color.RED);
                    return IsTouched;

                }
            }
        });

    }

    private void speakWords(String speech) {


        if (tts != null) {
            tts.setSpeechRate(0.8f);
            tts.setPitch(1.0f);
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    class Android_Gesture_Detector implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("Gesture ", " onDown");

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("Gesture ", " onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("Gesture ", " onSingleTapUp");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("Gesture ", " onShowPress");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("Gesture ", " onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.d("Gesture ", " onDoubleTapEvent");



            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("Gesture ", " onLongPress");


        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            Log.d("Gesture ", " onScroll");
            if (e1.getY() < e2.getY()){
                Log.d("Gesture ", " Scroll Down");
            }
            if(e1.getY() > e2.getY()){
                Log.d("Gesture ", " Scroll Up");
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {



            if (e1.getX() < e2.getX()) {
                Log.d("Gesture ", "Left to Right swipe: "+ e1.getX() + " - " + e2.getX());
//                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

                Intent i=new Intent(Intro_Keypad_Middle.this,TopScreenLeftRight.class);
                startActivity(i);
                onDestroy();

            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: "+ e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

                Intent i=new Intent(Intro_Keypad_Middle.this,MiddleScreenLeftRight.class);
                startActivity(i);
                onDestroy();

            }
            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", "Up to Down swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");




            }
            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", "Down to Up swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");

                Intent i=new Intent(Intro_Keypad_Middle.this,MainMenu_LearningModule.class);
                startActivity(i);
                onDestroy();

            }
            return true;

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);


    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(Intro_Keypad_Middle.this, MainMenu_LearningModule.class);
        startActivity(i);
        onDestroy();
    }

    public void onPause() {
        if (tts != null) {
            tts.stop();

        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Don't forget to stop and shutdown text to speech engine!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void onStop()
    {
        if (tts != null) {
            tts.stop();

        }
        super.onStop();
    }


}
