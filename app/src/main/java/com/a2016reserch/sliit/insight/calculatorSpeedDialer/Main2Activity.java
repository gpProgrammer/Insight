package com.a2016reserch.sliit.insight.calculatorSpeedDialer;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.a2016reserch.sliit.insight.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Main2Activity extends Activity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private GestureDetector mGestureDetector;
    SharedPreference sharedPreference;
    Android_Gesture_Detector an=new Android_Gesture_Detector();
    boolean addNewcontact=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //activity = getActivity();
        // Create an object of the Android_Gesture_Detector  Class
        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
// Create a GestureDetector
        mGestureDetector = new GestureDetector(this, android_gesture_detector);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sharedPreference=new SharedPreference();
        tts = new TextToSpeech(this,this);
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
                //  Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                // Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
            }
            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", "Up to Down swipe: " + e1.getX() + " - " + e2.getX());
                /// Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");
            }

            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", "Down to Up swipe: " + e1.getX() + " - " + e2.getX());
                // Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");
                if (addNewcontact) {
                    Intent intent = new Intent(Main2Activity.this, ContactDetails.class);
                    startActivity(intent);

                }
            }

            return true;

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
        // Return true if you have consumed the event, false if you haven't.
        // The default implementation always returns false.
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

    /** Called when the user clicks the Send button */
    public void view_key_pad(View v) {
        boolean AlreadyThere = false;
        // sharedPreference.removeAll(MainActivity.this);
        ArrayList<contacts> results = sharedPreference.getContacts(Main2Activity.this);
        //  ArrayList<String> results= (ArrayList<String>) getVal("1");
        int recount;
        // recount= results.size();
        // System.out.println(String.valueOf(recount));


        if(results==null || results.size()==0) {

            System.out.println("no data");
            tts.speak("There's no contact details added to the Contact list.If you like to enter new contact details swap up.", TextToSpeech.QUEUE_FLUSH, null);
            addNewcontact=true;
        }

        else {
            Intent intent = new Intent(this, SpeedDialer.class);
            startActivity(intent);

        }

    }

    public boolean deletedAllcontacts()
    {
        int count=0;
        ArrayList<contacts> results = sharedPreference.getContacts(Main2Activity.this);
        for (int i=0; i<results.size(); i++) {
            if(results.get(i).getCon_name()==""){
                count=count+1;
            }
            else
            {

            }

        }

        if(count==10)
        {
            return true;
        }

        return  false;

    }


    void setVal(String no,String name){
        List Listtasks = new ArrayList<String>();
        Listtasks.add(no);
        Listtasks.add(name);
        Set<String> contactDetail = new HashSet<String>(Listtasks);
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .edit()
                .putStringSet(no, contactDetail)
                .commit();
    }


    List<String> getVal(String no){
        Set<String> detail = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet(no, new HashSet<String>());
        List<String> contactDetail = new ArrayList<String>(detail);
        return contactDetail;
    }


}
