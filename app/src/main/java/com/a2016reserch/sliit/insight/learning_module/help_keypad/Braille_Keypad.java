package com.a2016reserch.sliit.insight.learning_module.help_keypad;

import android.app.Activity;
import android.content.Intent;
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

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Braille_Keypad extends Activity implements View.OnClickListener {

    TextToSpeech tts;
    Button btnTopLeft,btnTopRight,btnMiddleLeft,btnMiddleRight,btnBottomLeft,btnBottomRight;

    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braille__keypad);

        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        btnTopLeft=(Button) findViewById(R.id.button1);
        btnTopRight=(Button) findViewById(R.id.button2);
        btnMiddleLeft=(Button) findViewById(R.id.button3);
        btnMiddleRight=(Button) findViewById(R.id.button4);
        btnBottomLeft=(Button) findViewById(R.id.button5);
        btnBottomRight=(Button) findViewById(R.id.button6);


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
                    sleep(1000);
                    speakWords("Hi guys...");
                    sleep(1000);
                    speakWords("you are in braille keypad is activated");
                    sleep(2000);
                    speakWords("Touch the keypad and try to verify the learned key areas of the braille keypad");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();

        btnTopLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                speakWords("You are touching"+ btnTopLeft.getText().toString() + "area of the screen."+","+ "To go back to main menu, swipe the screen right to left");
                return false;
            }
        });

        btnTopRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                speakWords("You are touching"+ btnTopRight.getText().toString()+ "area of the screen." +","+ "To go back to main menu, swipe the screen right to left");
                return false;
            }
        });

        btnMiddleLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                speakWords("You are touching"+ btnMiddleLeft.getText().toString() + "area of the screen." +","+ "To go back to main menu, swipe the screen right to left");
                return false;
            }
        });

        btnMiddleRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                speakWords("You are touching"+ btnMiddleRight.getText().toString() + "area of the screen." +","+ "To go back to main menu, swipe the screen right to left");
                return false;
            }
        });

        btnBottomLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                speakWords("You are touching"+ btnBottomLeft.getText().toString()+ "area of the screen." +","+ "To go back to main menu, swipe the screen right to left");
                return false;
            }
        });

        btnBottomRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                speakWords("You are touching"+ btnBottomRight.getText().toString() + "area of the screen." +","+ "To go back to main menu, swipe the screen right to left");
                return false;
            }
        });
    }


    private void speakWords(String speech) {

        if (tts != null) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    public boolean check()
    {
        Boolean confirmA = getIntent().getExtras().getBoolean("nowinA");
        if(confirmA)
        {

        }
        return true;

    }

    @Override
    public void onClick(View view) {

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
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: "+ e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

                Intent intent =new Intent(Braille_Keypad.this,MainMenu_LearningModule.class);
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
            }
            return true;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);


    }


    public boolean LetterA(List<Integer> number)
    {

        int[] intArray = new int[number.size()];

        for (int i = 0; i < number.size(); i++) {
            intArray[i] = number.get(i);
        }

        int[] num1 = new int[] { R.id.button1 };

        if(Arrays.equals(intArray,num1))
        {
            speakWords("Correct Entry of Letter A, For Letter B, touch top Left and mid left of the screen");
            //   status=true;

        }

        else
        {
            speakWords("Incorrect entry of Letter A. Re enter Letter A by touching Top Left of the screen");
            //  mClickedButtonIds.clear();

        }
        return true;
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

    @Override
    public void onBackPressed() {

        //moveTaskToBack(false);

    }
}
