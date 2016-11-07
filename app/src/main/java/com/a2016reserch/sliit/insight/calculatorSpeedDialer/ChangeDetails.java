package com.a2016reserch.sliit.insight.calculatorSpeedDialer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.a2016reserch.sliit.insight.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ChangeDetails extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    boolean isStarted=false;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    boolean numberEntered=false;
    SharedPreference sharedPreference;
    Boolean deleteDetails=false;

    ArrayList<String> expression = new ArrayList<String>();
    private GestureDetector mGestureDetector;
    ArrayList<contacts> prDetails=new ArrayList<contacts>();
    int[] arrPrnumbers;
    Vibrator keypress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, android_gesture_detector);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_change_details);
        tts = new TextToSpeech(this, this);
        sharedPreference=new SharedPreference();

        instructions();
    }

    private void speakWords(String speech) {

        // speak straight away
        // tts = new TextToSpeech(this, this);
        if (tts != null) {

            tts.speak(speech, TextToSpeech.QUEUE_ADD, null);

        }
        while (tts.isSpeaking() ) {
        };
    }

    public void instructions()
    {
        final Thread logoTimer = new Thread() {
            public void run()
            {
                try
                {
                    if(!numberEntered) {
                        sleep(1000);
                        speakWords("You are now in Change Contact details forum.");
                        if(!numberEntered) {

                            sleep(1000);
                        }
                        if(!numberEntered) {
                            speakWords("To Add new contact details please press number 1.");
                        }

                        if(!numberEntered) {
                            sleep(1000);
                        }
                        if(!numberEntered) {
                            speakWords("To edit contact detail please press number 2.");
                        }
                        if(!numberEntered) {
                            sleep(1000);
                        }
                        if(!numberEntered) {
                            speakWords("To delete a contact detail please press number 3.");

                        }
                        if(!numberEntered) {
                            sleep(1000);
                        }
                        if(!numberEntered) {
                            speakWords("To read all the available details press number 4.");
                        }
                        if(!numberEntered) {
                            sleep(1000);
                        }
                        if(!numberEntered) {
                            speakWords("To repeat instruction please press number 5.");
                        }

                    }
                    else
                    {
                        Thread.interrupted();

                    }


                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();

    }


    public void onClick(View v) {
        AudioManager audioManager =
                (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN,1000.0f);
        keypress= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        keypress.vibrate(100);
        if (!isStarted) {
            isStarted = true;
            mClickedButtonIds.add(v.getId());
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            isStarted = false;
                            String ids;
                            ids = String.valueOf(mClickedButtonIds.size());
                            System.out.println(Arrays.toString(mClickedButtonIds.toArray()));
                            Toast.makeText(ChangeDetails.this, ids, Toast.LENGTH_LONG).show();
                            getNumber(mClickedButtonIds);
                            mClickedButtonIds.clear();
                        }
                    },
                    3000);

        } else {
            mClickedButtonIds.add(v.getId());
        }
    }



    public void getNumber(List<Integer> number) {

        int[] intArray = new int[number.size()];
        for (int i = 0; i < number.size(); i++) {
            intArray[i] = number.get(i);
        }

        int[] num1 = new int[] { R.id.button3 };
        int[] num2 = new int[] { R.id.button3, R.id.button5 };
        int[] num3 = new int[] { R.id.button3, R.id.button4 };
        int[] num4 = new int[] { R.id.button3, R.id.button4,R.id.button6};
        int[] num5 = new int[] { R.id.button3, R.id.button6 };

        Arrays.sort(intArray);

        if(Arrays.equals(intArray,num1))
        {
            System.out.println("call 1");
            ArrayList<contacts> results = sharedPreference.getContacts(ChangeDetails.this);
            if(results.size()==9) {
                speakWords("Contact list is full.You can't add any more");
            }
            else
            {
                Intent intent = new Intent(ChangeDetails.this, AddContactDetails.class);
                startActivity(intent);
                onDestroy();
            }
        }
        if(Arrays.equals(intArray,num2))
        {
            System.out.println("call 2");
            Intent intent = new Intent(ChangeDetails.this, EditContactDetails.class);
            startActivity(intent);
            onDestroy();
        }
        if(Arrays.equals(intArray,num3))
        {
            System.out.println("3");
            numberEntered=true;
            Intent intent = new Intent(ChangeDetails.this, DeleteContactDetails.class);
            startActivity(intent);
            onDestroy();

        }
        if(Arrays.equals(intArray,num4))
        {
            System.out.println("4");
            numberEntered=true;

            readAlldetails();
        }
        if(Arrays.equals(intArray,num5))
        {
            System.out.println("call 5");
            onDestroy();
            instructions();

        }
        if(!Arrays.equals(intArray, num1) && !Arrays.equals(intArray, num2)&&!Arrays.equals(intArray, num3) && !Arrays.equals(intArray, num4))
        {
            System.out.println("invalid sign");
        }

    }

    public void readAlldetails()
    {

        SharedPreference sharedPreference=new SharedPreference();
        ArrayList<contacts> results = sharedPreference.getContacts(ChangeDetails.this);
        ArrayList<ArrayList<String>> nodes = new ArrayList<ArrayList<String>>();
        for (int i=0; i<results.size(); i++)
        {



            // tts.setSpeechRate(1);

            // tts.speak("priority number, "+"  "+results.get(i).getPr_no()+"  "+"is, "+results.get(i).getCon_name()+" and number is,"+results.get(i).getCon_no(), TextToSpeech.QUEUE_FLUSH, null);
            speakWords("priority number, "+"  "+results.get(i).getPr_no()+"  "+"is, "+results.get(i).getCon_name()+" and number is,"+results.get(i).getCon_no());
            System.out.println("priority number, "+"  "+results.get(i).getPr_no()+"  "+"is, "+results.get(i).getCon_name()+" and number is,"+results.get(i).getCon_no());






            // System.out.println("priority number"+results.get(i).getPr_no()+"is"+results.get(i).getCon_name()+"and number is"+results.get(i).getCon_no());

            // speakWords("priority number"+results.get(i).getPr_no()+"is"+results.get(i).getCon_name()+"and number is"+results.get(i).getCon_no());
            // sleep(3000);

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
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH
                        || Math.abs(velocityY) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " bottomToTop");

                    if(expression.size()==0)
                    {
                        speakWords("You have not entered any element to backspace");
                    }
                    else
                    {
                        String value=expression.get(expression.size()-1);
                        expression.remove(expression.size()-1);
                        speakWords("removed last element which is "+value);
                    }


                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " topToBottom");
                }
            } else {
                if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {

                    Intent intent = new Intent(ChangeDetails.this, SpeedDialer.class);
                    startActivity(intent);
                    onDestroy();


                    Log.d("Gesture ", " RightToLeft");


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " LeftToright");
                }
            }
            return true;

        }
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

    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
        // Return true if you have consumed the event, false if you haven't.
        // The default implementation always returns false.
    }
}
