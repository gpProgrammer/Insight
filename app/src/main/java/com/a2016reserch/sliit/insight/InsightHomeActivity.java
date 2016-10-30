package com.a2016reserch.sliit.insight;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a2016reserch.sliit.insight.findplaces.MainActivity;

import java.util.Locale;

// Add a comment
// add branche home confi
public class InsightHomeActivity  extends Activity implements TextToSpeech.OnInitListener {

    private LocationManager locationManager;
    private String provider;
    int newCount=0;
    int start=-1;
    TextView name;
    String [] options = new String[] { "sd","cal","ms","n","gm","lm" };
    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;

    private TextToSpeech tts;
    // This code can be any value you want, its just a checksum.
    private static final int MY_DATA_CHECK_CODE = 1234;

    String addressField = "not available";

    Location location = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_insight_home);

        // Create an object of the Android_Gesture_Detector  Class
        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        // Fire off an intent to check if a TTS engine is installed
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);


        name=(TextView)findViewById(R.id.textView);
        name.setText("Welcome to Insight");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("Welcome to Insight ", TextToSpeech.QUEUE_FLUSH, null);
                // tts.speak("First enter contact persons name", TextToSpeech.QUEUE_FLUSH, null);

                //speak after 1000ms
            }
        }, 1000);
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


    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        super.onStart();

    }
    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {

        // tts shutdown!
        if (tts != null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onStop();

    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {

        // tts shutdown!
        if (tts != null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();


    }

    // deactivate back button
    @Override
    public void onBackPressed() {

        speakWords("Exit!");
        // onDestroy();
        moveTaskToBack(true);

    }


    /**
     * This is the callback from the TTS engine check, if a TTS is installed we
     * create a new TTS instance (which in turn calls onInit), if not then we will
     * create an intent to go off and install a TTS engine
     *
     * @param requestCode int Request code returned from the check for TTS engine.
     * @param resultCode  int Result code returned from the check for TTS engine.
     * @param data        Intent Intent returned from the TTS check.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                tts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    private void speakWords(String speech) {

        // speak straight away
        if (tts != null) {
            tts.setSpeechRate(1);
            //tts.speak(speech, TextToSpeech.QUEUE_ADD, null);

            // Drop all pending entries in the playback queue.
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);

        }


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
                //  view_key_pad();

            }
            if(start==-1)
            {
                // view_key_pad();

            }
            else if(start==1)
            {
                //  Intent intent = new Intent(Home.this, calculator.class);
                //  startActivity(intent);
                // onDestroy();

            }
            else if(start==2)
            {

//                onDestroy();
                Intent intent = new Intent(InsightHomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
            else if(start==3)
            {
                speakWords("messaging");

            }
            else if(start==4)
            {

                speakWords("Learning Module");
            }
            else if(start==5)
            {
                speakWords("Gaming Module");
            }
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
            speakWords("Help option");
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
            name.setText("Speed Dialler");
            speakWords("Speed Dialler");

        }
        else if(no==1)
        {
            name.setText("Calculator");
            speakWords("Calculator");
        }
        else if(no==2)
        {
            name.setText("Navigation Base Service");
            speakWords("Navigation Base Service");
        }
        else if(no==3)
        {
            name.setText("Messenger");
            speakWords("Messenger");
        }
        else if(no==4)
        {
            name.setText("Learning Module");
            speakWords("Learning Module");
        }
        else if(no==5)
        {
            name.setText("Game");
            speakWords("Game");
        }
    }
}
