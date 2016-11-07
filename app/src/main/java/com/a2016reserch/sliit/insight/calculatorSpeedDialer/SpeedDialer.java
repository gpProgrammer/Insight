package com.a2016reserch.sliit.insight.calculatorSpeedDialer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.a2016reserch.sliit.insight.InsightHomeActivity;
import com.a2016reserch.sliit.insight.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SpeedDialer extends Activity implements TextToSpeech.OnInitListener, View.OnClickListener {

    private String[] numbers;
    ArrayList<String> ar = new ArrayList<String>();
    private TextToSpeech tts;
    boolean isStarted = false;
    SharedPreference sharedPreference;
    boolean changeDetails;
    private GestureDetector mGestureDetector;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    Vibrator keypress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create an object of the Android_Gesture_Detector  Class
        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector();
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_speed_dialer);
        tts = new TextToSpeech(this, this);
        sharedPreference = new SharedPreference();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("you are in Speed Dialer Option.Please enter the priority number to get the call.If you want to add new detail,edit or delete existing details swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                changeDetails = true;
                // tts.speak("First enter contact persons name", TextToSpeech.QUEUE_FLUSH, null);

                //speak after 1000ms
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {

        AudioManager audioManager =
                (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN, 1000.0f);

        keypress = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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
                            Toast.makeText(SpeedDialer.this, ids, Toast.LENGTH_LONG).show();
                            getCall(mClickedButtonIds);
                            mClickedButtonIds.clear();
                        }
                    },
                    3000);

        } else {
            mClickedButtonIds.add(v.getId());
        }



      /*  mClickedButtonIds.add(v.getId());

        long t= System.currentTimeMillis();
        long end = t+3000;
        while(System.currentTimeMillis() < end) {


            // do something
            //someFunction(v);
            onClick(v);
            // pause to avoid churning
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




        String ids;
        ids = String.valueOf(mClickedButtonIds.size());
        System.out.println(Arrays.toString(mClickedButtonIds.toArray()));
        Toast.makeText(key_pad.this,ids , Toast.LENGTH_LONG).show();
*/
        //  getNumber(v);



       /* mClickedButtonIds.add(v.getId());
     //   mClickedButtonIds.remove(0);
        if (mClickedButtonIds.size() > 0) {
            mClickedButtonIds.remove(0);

            if (mClickedButtonIds.get(0) == mDesiredOrder[0]
                    && mClickedButtonIds.get(1) == mDesiredOrder[1]
                    ) {
                // # Do something, buttons are clicked in the order you want
                Toast.makeText(key_pad.this, "success", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(key_pad.this, "wrong", Toast.LENGTH_LONG).show();
            }
        }*/

        /*long t= System.currentTimeMillis();
        long end = t+5000;
        while(System.currentTimeMillis() < end) {
            // do something

            try {
            if (v.getId() == R.id.button1 ) {
                ar.add("1");
                Toast.makeText(key_pad.this, "button1",Toast.LENGTH_LONG).show();
            }  if (v.getId() == R.id.button2) {
                ar.add("2");
                Toast.makeText(key_pad.this,"button2", Toast.LENGTH_LONG).show();
            } if (v.getId() == R.id.button3){
                ar.add("3");
                Toast.makeText(key_pad.this, "button3",Toast.LENGTH_LONG).show();
            }

            // pause to avoid churning


                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        String[] bob = { "1", "2" };
        if(ar.equals(bob))
        {
            Toast.makeText(key_pad.this, "success",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(key_pad.this, "invalid",Toast.LENGTH_LONG).show();
        }*/

        /*switch(v.getId())
        {
            case R.id.button1 :
                Toast.makeText(key_pad.this, "button1",Toast.LENGTH_LONG).show();
                ar.add("1");
                //append("1",output);
                //break;
            case R.id.button2 :
                Toast.makeText(key_pad.this,"button2", Toast.LENGTH_LONG).show();
                ar.add("2");
               // break;
            case R.id.button3 :
                Toast.makeText(key_pad.this,"button3",Toast.LENGTH_LONG).show();
                ar.add("3");
               // break;


        }*/






       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //displayData();
                String[] bob = { "1", "2" };
                if(ar.equals(bob))
                {
                    Toast.makeText(key_pad.this,"b 1 and 2",Toast.LENGTH_LONG).show();

                }
            }
        }, 20000);*/
    }

    public int FindPriorityNumber(String no) {
        ArrayList<contacts> results = sharedPreference.getContacts(SpeedDialer.this);

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getPr_no().contentEquals(no)) {
                return i;
            }
        }

        return -1;

    }

    public void getCall(List<Integer> number) {


        int[] intArray = new int[number.size()];
        for (int i = 0; i < number.size(); i++) {
            intArray[i] = number.get(i);
        }


        int[] num1 = new int[]{R.id.button3};
        int[] num2 = new int[]{R.id.button3, R.id.button5};
        int[] num3 = new int[]{R.id.button3, R.id.button4};
        int[] num4 = new int[]{R.id.button3, R.id.button4, R.id.button6};
        int[] num5 = new int[]{R.id.button3, R.id.button6};
        int[] num6 = new int[]{R.id.button3, R.id.button4, R.id.button5};
        int[] num7 = new int[]{R.id.button3, R.id.button4, R.id.button5, R.id.button6};
        int[] num8 = new int[]{R.id.button3, R.id.button5, R.id.button6};
        int[] num9 = new int[]{R.id.button4, R.id.button5};


        Arrays.sort(intArray);


        //speakOut();

        if (intArray == null) {
            System.out.println("Please enter the number");

        }
        if (Arrays.equals(intArray, num1)) {

            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 1");
            int index = FindPriorityNumber("1");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 1");

            } else {

                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }

        }
        if (Arrays.equals(intArray, num2)) {
            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 2");
            int index = FindPriorityNumber("2");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 2");

            } else {

                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }
        }
        if (Arrays.equals(intArray, num3)) {
            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 3");
            int index = FindPriorityNumber("3");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 3");

            } else {
                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }
        }
        if (Arrays.equals(intArray, num4)) {
            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 4");
            int index = FindPriorityNumber("4");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 4");

            } else {
                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }
        }
        if (Arrays.equals(intArray, num5)) {
            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 5");
            int index = FindPriorityNumber("5");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 5");

            } else {
                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }
        }
        if (Arrays.equals(intArray, num6)) {
            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 6");
            int index = FindPriorityNumber("6");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 6");

            } else {
                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }
        }
        if (Arrays.equals(intArray, num7)) {
            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 7");
            int index = FindPriorityNumber("7");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 7");

            } else {
                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }
        }
        if (Arrays.equals(intArray, num8)) {
            ArrayList<contacts> prNo = sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 8");
            int index = FindPriorityNumber("8");
            if (index == -1) {
                speakWords("There's no contact number assign to priority number 8");

            } else {
                speakWords("Calling " + prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        }
        if(Arrays.equals(intArray,num9))
        {
            ArrayList<contacts> prNo=sharedPreference.getContacts(SpeedDialer.this);
            System.out.println("call 9");
            int index=FindPriorityNumber("9");
            if(index==-1)
            {
                speakWords("There's no contact number assign to priority number 9");

            }
            else
            {
                speakWords("Calling "+prNo.get(index).getCon_name());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + prNo.get(index).getCon_no()));
                startActivity(callIntent);
            }
        }
        if(!Arrays.equals(intArray, num1) && !Arrays.equals(intArray, num2) && !Arrays.equals(intArray, num3) && !Arrays.equals(intArray, num5) && !Arrays.equals(intArray, num6) && !Arrays.equals(intArray, num4) && !Arrays.equals(intArray, num7) && !Arrays.equals(intArray, num8) && !Arrays.equals(intArray, num9))
        {
            System.out.println("invalid sign");
            tts.speak("Invalid sign.Please enter priority number correctly", TextToSpeech.QUEUE_FLUSH, null);
        }



    }


    private void speakWords(String speech) {

        // speak straight away
        if (tts != null) {

            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);

            while (tts.isSpeaking())
            {

            }

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


                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " topToBottom");
                }
            } else {
                if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
                    Intent intent = new Intent(SpeedDialer.this, InsightHomeActivity.class);
                    startActivity(intent);
                    onDestroy();
                    Log.d("Gesture ", " RightToLeft");


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {

                    Intent intent = new Intent(SpeedDialer.this, ChangeDetails.class);
                    startActivity(intent);
                    onDestroy();
                    Log.d("Gesture ", " LeftToright");
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
