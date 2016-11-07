package com.a2016reserch.sliit.insight.calculatorSpeedDialer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
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

public class DeleteContactDetails extends Activity implements TextToSpeech.OnInitListener {

    private GestureDetector mGestureDetector;
    private TextToSpeech tts;
    SharedPreference sharedPreference;
    boolean confirmDeletion=false;
    int no=-1;
    ArrayList<contacts> prNo1;
    boolean isStarted=false;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
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
        setContentView(R.layout.activity_delete_contact_details);
        tts = new TextToSpeech(this, this);
        sharedPreference=new SharedPreference();



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("Please enter priority number to delete details", TextToSpeech.QUEUE_ADD, null);
                // tts.speak("First enter contact persons name", TextToSpeech.QUEUE_FLUSH, null);

                //speak after 1000ms
            }
        }, 1000);
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
                            Toast.makeText(DeleteContactDetails.this, ids, Toast.LENGTH_LONG).show();
                            deleteNumber(mClickedButtonIds);
                            mClickedButtonIds.clear();
                        }
                    },
                    3000);

        } else {
            mClickedButtonIds.add(v.getId());
        }
    }


    public void deleteNumber(List<Integer> number)
    {
        int[] intArray = new int[number.size()];
        for (int i = 0; i < number.size(); i++) {
            intArray[i] = number.get(i);
        }

        int[] num1 = new int[] { R.id.button3 };
        int[] num2 = new int[] { R.id.button3, R.id.button5 };
        int[] num3 = new int[] { R.id.button3, R.id.button4 };
        int[] num4 = new int[] { R.id.button3, R.id.button4,R.id.button6};
        int[] num5 = new int[] { R.id.button3, R.id.button6 };
        int[] num6 = new int[] { R.id.button3, R.id.button4,R.id.button5 };
        int[] num7 = new int[] { R.id.button3, R.id.button4,R.id.button5,R.id.button6 };
        int[] num8 = new int[] { R.id.button3, R.id.button5,R.id.button6 };
        int[] num9 = new int[] { R.id.button4,R.id.button5 };
        Arrays.sort(intArray);


        if(Arrays.equals(intArray,num1))
        {
            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);


            System.out.println("1");
            confirmDeletion=true;
            no=FindPriorityNumber("1");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if(Arrays.equals(intArray,num2))
        {


            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("2");
            confirmDeletion=true;
            no=FindPriorityNumber("2");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if(Arrays.equals(intArray,num3))
        {

            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("3");
            confirmDeletion=true;
            no=FindPriorityNumber("3");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if(Arrays.equals(intArray,num4))
        {
            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("4");
            confirmDeletion=true;
            no=FindPriorityNumber("4");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if(Arrays.equals(intArray,num5))
        {

            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("5");
            confirmDeletion=true;
            no=FindPriorityNumber("5");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if(Arrays.equals(intArray,num6))
        {

            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("6");
            confirmDeletion=true;
            no=FindPriorityNumber("6");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if(Arrays.equals(intArray,num7))
        {

            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("7");
            confirmDeletion=true;
            no=FindPriorityNumber("7");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if(Arrays.equals(intArray,num8))
        {
            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("8");
            confirmDeletion=true;
            no=FindPriorityNumber("8");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if(Arrays.equals(intArray,num9))
        {
            prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
            System.out.println("9");
            confirmDeletion=true;
            no=FindPriorityNumber("9");
            if(no!=-1) {
                tts.speak("Dou You want to delete " + prNo1.get(no).getCon_name() + " Contact details,if yes swap right", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if(!Arrays.equals(intArray, num1) && !Arrays.equals(intArray, num2) && !Arrays.equals(intArray, num3) && !Arrays.equals(intArray, num5) && !Arrays.equals(intArray, num6) && !Arrays.equals(intArray, num4) && !Arrays.equals(intArray, num7) && !Arrays.equals(intArray, num8) && !Arrays.equals(intArray, num9))
        {
            System.out.println("invalid sign");
        }



    }

    public int FindPriorityNumber(String no)
    {
        ArrayList<contacts> results=sharedPreference.getContacts(DeleteContactDetails.this);

        for (int i=0; i<results.size(); i++) {
            if(results.get(i).getPr_no().contentEquals(no))
            {
                return i;
            }
        }

        return -1;

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




                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " topToBottom");
                }
            } else {
                if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {

                    Intent intent = new Intent(DeleteContactDetails.this, ChangeDetails.class);
                    startActivity(intent);
                    onDestroy();

                    Log.d("Gesture ", " RightToLeft");


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " LeftToright");

                    if(confirmDeletion)
                    {
                        prNo1=sharedPreference.getContacts(DeleteContactDetails.this);
                        sharedPreference.removeAdetail(DeleteContactDetails.this,prNo1.get(no),no);
                        confirmDeletion=false;
                        no=-1;
                        tts.speak("Details deleted.Please enter another priority number to delete details", TextToSpeech.QUEUE_ADD, null);
                    }



                }
            }
            return true;

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
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
        // Return true if you have consumed the event, false if you haven't.
        // The default implementation always returns false.
    }
}
