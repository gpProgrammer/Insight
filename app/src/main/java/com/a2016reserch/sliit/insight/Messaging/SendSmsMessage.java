package com.a2016reserch.sliit.insight.Messaging;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;

import java.util.Locale;
import com.a2016reserch.sliit.insight.R;
public class SendSmsMessage extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;
    String phone="", text="", msg="", text1="";
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms_message);

        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector();
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        tts=new TextToSpeech(this,this);

        message = (EditText) findViewById(R.id.editText2);



        Thread logoTimer = new Thread() {
            public void run() {
                try {

                    sleep(1000);
                    speakWords("Enter your message after tapping on the screen.  Then swipe left to right to read out the message");



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();


        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do what you want
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //do what you want
                speakWords(s.toString());



            }

            @Override
            public void afterTextChanged(Editable s) {
                //do what you want
            }
        });

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
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
        String word= String.valueOf(event.getUnicodeChar());
        speakWords(word);


        return super.dispatchKeyEvent(event);


    }
    /*
        public void KeyCodeEventAsChar(int keyCode, KeyEvent event) {

            char pressedKey;
            if(keyCode== event.KEYCODE_A){


            }



        }*/
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


                Intent intent = new Intent(SendSmsMessage.this, ReadMessage.class);
                Intent i = getIntent();
                Bundle b = new Bundle();

                b = i.getBundleExtra("data1");

                phone = (String) b.getString("number");
                System.out.println(phone);
                text = phone+",";
                msg=(String) message.getText().toString();

                if (msg.length()==0){

                    speakWords("type your message. ");
                }

                else if (msg.length()!=0){
                    text1=text+msg;
                    b.putString("value",text1);
                    intent.putExtra("data2", b);
                    startActivity(intent);
                }




            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

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
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}

