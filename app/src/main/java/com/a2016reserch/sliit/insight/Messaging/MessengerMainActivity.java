package com.a2016reserch.sliit.insight.Messaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a2016reserch.sliit.insight.R;

import java.util.Locale;

/**
 * Created by Nimmi on 11/4/2016.
 */
public class MessengerMainActivity extends Activity implements TextToSpeech.OnInitListener{
    private GestureDetector mGestureDetector;
    private TextToSpeech tts;
    String [] options = new String[] { "sd","cal","ms","n","gm","lm" };
    int newCount=0;
    int start=-1;
    TextView name;

    boolean addNewcontact=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Create an object of the Android_Gesture_Detector  Class
        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, android_gesture_detector);
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_insight_home);

        tts = new TextToSpeech(this,this);


        name=(TextView)findViewById(R.id.txtName);
        name.setText("Messenger");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("You are in messenger now, Long press to get help. Swipe to go through the menu", TextToSpeech.QUEUE_FLUSH, null);

            }
        }, 1000);



       /* super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/


    }

    /*  public void goToInbox(View view) {
          Intent intent = new Intent(MessengerMainActivity.this, ReceiveSms.class);
          startActivity(intent);
      }

      public void goToCompose(View view) {
          Intent intent = new Intent(MessengerMainActivity.this, SendSmsPhone.class);
          startActivity(intent);
      }


      public void goToSentItems(View view){

          Intent intent = new Intent(MessengerMainActivity.this, SentItems.class);
          startActivity(intent);
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

    private void speakWords(String speech) {

        // speak straight away
        if (tts != null) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        while (tts.isSpeaking())
        {

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

        static final int SWIPE_MIN_DISTANCE = 120;
        static final int SWIPE_MAX_OFF_PATH = 250;
        static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("Gesture ", " onDown");
            // Toast.makeText(calculator.this, "DOWNNNNN", Toast.LENGTH_LONG).show();
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
            int val=start;
            if(start==0)
            {


            }
            if(start==-1)
            {


            }
            else if(start==1)
            {
                //// Intent intent = new Intent(Home.this, calculator.class);
                //// startActivity(intent);
                //// onDestroy();
                speakWords("Compose Message");
                Intent intent = new Intent(MessengerMainActivity.this, SendSmsPhone.class);
                startActivity(intent);
                onDestroy();

            }
            else if(start==2)
            {
                speakWords("Message Inbox");
                Intent intent = new Intent(MessengerMainActivity.this, ReceiveSms.class);
                startActivity(intent);
                onDestroy();

            }
            else if(start==3)
            {
                speakWords("Sent Items");
                Intent intent = new Intent(MessengerMainActivity.this, SentItems.class);
                startActivity(intent);
                onDestroy();

            }
           /* else if(start==4)
            {

                speakWords("Scheduler");
            }
            else if(start==5)
            {
                speakWords("Gaming Module");
            }*/
            else
            {
                System.out.println(Integer.toString(start));
                //  speakWords("invalid");

            }

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
            speakWords("Swipe from right to left to go through the menu,  double tap to go inside an item");
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
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH
                        || Math.abs(velocityY) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " bottomToTop");
                    if(addNewcontact) {
                        //// Intent intent = new Intent(Home.this, ContactDetails.class);
                        ////startActivity(intent);

                    }




                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " topToBottom");

                }
            } else {
                if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {



                    Log.d("Gesture ", " RightToLeft");

                    start=start+1;
                    if(start==0)
                    {
                        start=1;
                    }
                    for (int i = start; i <=options.length+1; i++) {

                        if(start>5)
                        {

                            System.out.println(options[0]);
                            viewText(0);
                            start=0;

                        }


                        else
                        {
                            System.out.println(options[(start)]);
                            viewText(start);
                            //  start=start+1;

                        }

                        break;
                    }

                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {

                    start=start-1;
                    for (int i = start; i >= -2 ; i--) {
                        if (start <= -1) {
                            start = 5;
                            System.out.println(options[(start)]);
                            viewText(start);

                        } else {

                            System.out.println(options[(start)]);
                            viewText(start);
                            //start=start-1;
                        }
                        // newCount = start;
                        break;
                    }
                    //start=start-1;

                    Log.d("Gesture ", " LeftToright");
                }
            }
            return true;

        }
    }


    public void viewText(int no)
    {
        if(no==0 || no==-1)
        {
            name.setText("Messenger");
            speakWords("Messenger");

        }
        else if(no==1)
        {
            name.setText("Compose Message");
            speakWords("Compose message");
        }
        else if(no==2)
        {
            name.setText("Message Inbox");
            speakWords("message inbox");
        }
        else if(no==3)
        {
            name.setText("Sent Items");
            speakWords("sent items");
        }
        /*else if(no==4)
        {
            name.setText("Scheduler");
            speakWords("Scheduler ");
        }
        else if(no==5)
        {
            name.setText("Game");
            speakWords("Game");
        }*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /* if (flingDetector.onTouchEvent(event)) {
            return true;
        }*/
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);

        // return super.onTouchEvent(event);


    }
}

