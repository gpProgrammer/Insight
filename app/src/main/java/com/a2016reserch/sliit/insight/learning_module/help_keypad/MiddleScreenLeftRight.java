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

public class MiddleScreenLeftRight extends Activity {

    TextToSpeech tts;
    Button btnTopLeft, btnTopRight, btnmidLeft, btnmidRight, btnBottomLeft, btnBottomRight;
    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;
    boolean statusMidLeft, isStatusMidRight = false;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_screen_left_right);

        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector();

        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnTopLeft = (Button) findViewById(R.id.button8);
        btnTopRight = (Button) findViewById(R.id.button10);
        btnmidLeft = (Button) findViewById(R.id.button13);
        btnmidRight = (Button) findViewById(R.id.button14);
        btnBottomLeft = (Button) findViewById(R.id.button5);
        btnBottomRight = (Button) findViewById(R.id.button6);

        btnTopLeft.setEnabled(false);
        btnTopRight.setEnabled(false);
        btnmidLeft.setEnabled(true);
        btnmidRight.setEnabled(true);
        btnBottomLeft.setEnabled(false);
        btnBottomRight.setEnabled(false);

        btnmidLeft.setBackgroundColor(Color.parseColor("#f9e200"));
        btnmidRight.setBackgroundColor(Color.parseColor("#f9e200"));

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
                    speakWords("Let's identify the middle area in detail");
                    sleep(4000);
                    speakWords("Middle area has divided into two main parts");
                    sleep(4000);
                    speakWords("Left side of the middle area known as Middle Left");
                    sleep(4000);
                    speakWords("Try to touch on left side of the middle area");


                    sleep(5000);
                    btnmidLeft.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            vibrator.vibrate(500);
                            btnmidLeft.setBackgroundColor(Color.YELLOW);
                            speakWords("You are touching" + btnmidLeft.getText().toString() + "area of the screen." + "Now try to touch on right side of the middle area");

                            statusMidLeft = true;
                            return statusMidLeft;
                        }
                    });


                    sleep(5000);
                    btnmidRight.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            vibrator.vibrate(1000);
                            btnmidRight.setBackgroundColor(Color.parseColor("#FFA500"));
                            speakWords("You are touching" + btnmidRight.getText().toString() + " area of the screen");

                            Thread thread = new Thread() {
                                public void run() {
                                    try {

                                        sleep(2000);
                                        speakWords("You are touching" + btnmidRight.getText().toString() + " area of the screen");
                                        sleep(5000);
                                        speakWords("To learn about Bottom area swipe the screen from right to left ");
                                        sleep(5000);
                                        speakWords("If you want to go back to learn the top area swipe the screen from left to right");
                                    } catch (Exception e) {

                                    }
                                }
                            };

                            thread.start();

                            return true;
                        }
                    });



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();
    }

    private void speakWords(String speech) {

        // speak straight away
        if (tts != null) {
            tts.setSpeechRate(0.8f);
            tts.setPitch(1.0f);
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
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
            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", " Scroll Down");
            }
            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", " Scroll Up");
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() < e2.getX()) {
                Log.d("Gesture ", "Left to Right swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

                Intent intent = new Intent(MiddleScreenLeftRight.this, Intro_Keypad_Middle.class);
                startActivity(intent);
                onDestroy();


            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

                Intent intent = new Intent(MiddleScreenLeftRight.this, Intro_Keypad_Bottom.class);
                startActivity(intent);
                onDestroy();


            }
            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", "Up to Down swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");


            }
            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", "Down to Up swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");

                Intent i = new Intent(MiddleScreenLeftRight.this, MainMenu_LearningModule.class);
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
    public void onBackPressed() {
        //moveTaskToBack(false);

    }

}
