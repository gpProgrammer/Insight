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

public class Letter_M extends Activity implements  TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    boolean isStarted = false;
    String statusM = "incorrect";
    boolean nowinM = false;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    Button b1, b2, b3, b4, b5, b6;
    Vibrator vibeA;
    boolean btnStateM;

    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter__m);

        btnStateM = true;

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
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(true);
        b6.setEnabled(false);


        Thread logoTimer = new Thread() {
            public void run() {
                try {

                    sleep(2000);
                    speakWords("For letter M by giving first give single tap on the Top Left side, then single tap of Top Right side and finally single tap on the Bottom Left side of the screen");


                    nowinM = true;


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

                            LetterM(mClickedButtonIds);
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

                Intent intent=new Intent(Letter_M.this,Letter_L.class);
                startActivity(intent);
                onDestroy();
            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");


                if(statusM == "correct") {
                    Intent intent = new Intent(Letter_M.this, Letter_N.class);
                    // intent.putExtra("statusM", "correct");
                    startActivity(intent);
                    b1.setBackgroundResource(R.drawable.buttons);
                    b2.setBackgroundResource(R.drawable.buttons);
                    b5.setBackgroundResource(R.drawable.buttons);
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

                Intent intent = new Intent(Letter_M.this, Braille_Tutorials.class);
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

    public boolean LetterM(List<Integer> number) {

        if (nowinM) {
            int[] intArray = new int[number.size()];

            for (int i = 0; i < number.size(); i++) {
                intArray[i] = number.get(i);
            }

            int[] num13 = new int[]{R.id.button1, R.id.button2, R.id.button5};

            if (Arrays.equals(intArray, num13)) {
                if(btnStateM){
                    btnStateM = false;
                    b1.setBackgroundResource(R.drawable.reddot);
                    b2.setBackgroundResource(R.drawable.reddot);
                    b5.setBackgroundResource(R.drawable.reddot);

                }
                vibeA = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibeA.vibrate(500);
                Thread logoTimer = new Thread() {
                    public void run() {
                        try {
                            sleep(2000);
                            speakWords("Correct Entry of Letter M. For Letter N, swipe the screen from right to left");
                            statusM = "correct";
                            sleep(6000);
                            speakWords("To learn Letter L again, swipe screen from left to right");



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                logoTimer.start();

            } else {
                b1.setBackgroundResource(R.drawable.buttons);
                b2.setBackgroundResource(R.drawable.buttons);
                b5.setBackgroundResource(R.drawable.buttons);
                speakWords("Incorrect entry of Letter M. Re enter letter M by giving first give single tap on the Top Left side, then single tap of Top Right side and finally single tap on the Bottom Left side of the screen");

            }
            return true;
        }

        else
        {
            return false;
        }
    }
}
