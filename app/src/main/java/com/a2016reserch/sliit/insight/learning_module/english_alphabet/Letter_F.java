package com.a2016reserch.sliit.insight.learning_module.english_alphabet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.a2016reserch.sliit.insight.R;
import com.a2016reserch.sliit.insight.learning_module.help_keypad.Braille_Tutorials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Letter_F extends Activity implements  TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    boolean isStarted = false;
    String statusF = "incorrect";
    boolean nowinF = false;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    Button b1, b2, b3, b4, b5, b6;
    Vibrator vibeA;
    boolean btnStateF;

    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter__f);

        btnStateF = true;

        tts=new TextToSpeech(this,this);

        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector();
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);

        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);


        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    speakWords("For letter F  first give single tap on the Top Left side, then single tap of Top Right side and finally give single tap on Mid Left of the screen");
                    nowinF = true;


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();

    }

    private void speakWords(String speech) {

        if (tts != null) {

            tts.setSpeechRate(0.9f);
            tts.setPitch(1.0f);

            tts.speak(speech, TextToSpeech.QUEUE_ADD, null);

        }
        while (tts.isSpeaking() ) {
        };
    }

    public void onClick(final View view) {
        if (!isStarted) {
            isStarted = true;
            mClickedButtonIds.add(view.getId());

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            isStarted = false;

                            LetterF(mClickedButtonIds);
                            mClickedButtonIds.clear();

                        }
                    },
                    3000);

        } else {
            mClickedButtonIds.add(view.getId());
        }
    }

    @Override
    public void onBackPressed() {

        //moveTaskToBack(false);

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {

            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
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

                Intent intent=new Intent(Letter_F.this,Letter_E.class);
                startActivity(intent);
                onDestroy();
            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

                if(statusF=="correct") {
                    Intent intent = new Intent(Letter_F.this, Letter_G.class);
                    //  intent.putExtra("statusF", "correct");
                    startActivity(intent);
                    b1.setBackgroundResource(R.drawable.buttons);
                    b2.setBackgroundResource(R.drawable.buttons);
                    b3.setBackgroundResource(R.drawable.buttons);
                    onDestroy();
                }

                else
                {
                    speakWords("Sorry, You did not give the correct answer. Try again");
                    finish();
                    startActivity(getIntent());
                }


            }
            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", "Up to Down swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");

                Intent intent = new Intent(Letter_F.this, Braille_Tutorials.class);
                startActivity(intent);
                onDestroy();
            }
            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", "Down to Up swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");
            }
            return true;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);


    }

    public boolean LetterF(List<Integer> number) {

        if (nowinF) {
            int[] intArray = new int[number.size()];

            for (int i = 0; i < number.size(); i++) {
                intArray[i] = number.get(i);
            }

            int[] num6 = new int[]{R.id.button1, R.id.button2, R.id.button3};

            if (Arrays.equals(intArray, num6)) {
                if(btnStateF){
                    btnStateF = false;
                    b1.setBackgroundResource(R.drawable.reddot);
                    b2.setBackgroundResource(R.drawable.reddot);
                    b3.setBackgroundResource(R.drawable.reddot);

                }
                vibeA = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibeA.vibrate(500);
                Thread logoTimer = new Thread() {
                    public void run() {
                        try {
                            sleep(2000);
                            speakWords("Correct Entry of Letter F. For Letter G, swipe the screen right to left");
                            statusF = "correct";
                            sleep(6000);
                            speakWords("To learn Letter E again, swipe screen from left to right");



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                logoTimer.start();

            } else {
                b1.setBackgroundResource(R.drawable.buttons);
                b2.setBackgroundResource(R.drawable.buttons);
                b3.setBackgroundResource(R.drawable.buttons);
                speakWords("Incorrect entry of Letter F. Re enter letter F  first give single tap on the Top Left side, then single tap of Top Right side and finally give single tap on Mid Left of the screen");

            }
            return true;
        }

        else
        {
            return false;
        }
    }
}
