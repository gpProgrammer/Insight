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

import com.a2016reserch.sliit.insight.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class AddContactDetails extends Activity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    boolean isStarted=false;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    ArrayList<String> expression = new ArrayList<String>();
    boolean confirmName=false;
    boolean confirmNumber=false;
    private GestureDetector mGestureDetector;
    String ContactName="";
    String ContactNu="";
    SharedPreference sharedPreference=new SharedPreference();
    int[] arrPrnumbers=new int[9];
    boolean found;
    int[] remainPrnumbers=new int[9];
    Vibrator keypress;

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
        setContentView(R.layout.activity_add_contact_details);
        tts = new TextToSpeech(this, this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("First enter Contact persons name And swap left to confirm name", TextToSpeech.QUEUE_FLUSH, null);

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
        if(!isStarted)
        {
            isStarted=true;
            mClickedButtonIds.add(v.getId());
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            isStarted=false;
                            System.out.println(Arrays.toString(mClickedButtonIds.toArray()));
                            // Toast.makeText(calculator.this,ids , Toast.LENGTH_LONG).show();
                            getName(mClickedButtonIds);
                            mClickedButtonIds.clear();
                        }
                    },
                    3000);

        }
        else
        {
            mClickedButtonIds.add(v.getId());
        }

    }

    public void getName(List<Integer> number) {


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
        int[] num0 = new int[] { R.id.button4,R.id.button5,R.id.button6 };
        int[] a=new int[]{R.id.button1};
        int[] b=new int[]{R.id.button1, R.id.button3};
        int[] c=new int[]{R.id.button1, R.id.button2};
        int[] d=new int[]{R.id.button1, R.id.button2,R.id.button4};
        int[] e=new int[]{R.id.button1, R.id.button4};
        int[] f=new int[]{R.id.button1, R.id.button2,R.id.button3};
        int[] g=new int[]{R.id.button1, R.id.button2,R.id.button3,R.id.button4};
        int[] h=new int[]{R.id.button1, R.id.button3,R.id.button4};
        int[] i=new int[]{R.id.button2,R.id.button3};
        int[] j=new int[]{R.id.button2,R.id.button3,R.id.button4};
        int[] k=new int[]{R.id.button1, R.id.button5};
        int[] l=new int[]{R.id.button1, R.id.button3,R.id.button5};
        int[] m=new int[]{R.id.button1, R.id.button2,R.id.button5};
        int[] n=new int[]{R.id.button1, R.id.button2,R.id.button4,R.id.button5};
        int[] o=new int[]{R.id.button1, R.id.button4,R.id.button5};
        int[] p=new int[]{R.id.button1, R.id.button2,R.id.button3,R.id.button5};
        int[] q=new int[]{R.id.button1, R.id.button2,R.id.button3,R.id.button4,R.id.button5};
        int[] r=new int[]{R.id.button1, R.id.button3,R.id.button4,R.id.button5};
        int[] s=new int[]{R.id.button2,R.id.button3,R.id.button5};
        int[] t=new int[]{R.id.button2,R.id.button3,R.id.button4,R.id.button5};
        int[] u=new int[]{R.id.button1,R.id.button5, R.id.button6};
        int[] v=new int[]{R.id.button1, R.id.button3,R.id.button5, R.id.button6};
        int[] w=new int[]{R.id.button2,R.id.button3,R.id.button4,R.id.button6};
        int[] x=new int[]{R.id.button1, R.id.button2,R.id.button5,R.id.button6};
        int[] y=new int[]{R.id.button1, R.id.button2,R.id.button4,R.id.button5,R.id.button6};
        int[] z=new int[]{R.id.button1, R.id.button4,R.id.button5,R.id.button6};


        Arrays.sort(intArray);


        if (intArray == null){
            System.out.println("Please enter the number");

        }
        if(Arrays.equals(intArray,num1))
        {
            System.out.println("1");
            tts.speak("1", TextToSpeech.QUEUE_FLUSH, null);
            expression.add("1");

        }
        if(Arrays.equals(intArray,num2))
        {
            System.out.println("2");
            expression.add("2");
            tts.speak("2", TextToSpeech.QUEUE_FLUSH, null);

        }
        if(Arrays.equals(intArray,num3))
        {
            System.out.println("3");
            expression.add("3");
            tts.speak("3", TextToSpeech.QUEUE_FLUSH, null);

        }
        if(Arrays.equals(intArray,num4))
        {
            System.out.println("4");
            expression.add("4");
            tts.speak("4", TextToSpeech.QUEUE_FLUSH, null);

        }
        if(Arrays.equals(intArray,num5))
        {
            System.out.println("5");
            expression.add("5");
            tts.speak("5", TextToSpeech.QUEUE_FLUSH, null);

        }
        if(Arrays.equals(intArray,num6))
        {
            System.out.println("6");
            expression.add("6");
            tts.speak("6", TextToSpeech.QUEUE_FLUSH, null);


        }
        if(Arrays.equals(intArray,num7))
        {
            System.out.println("7");
            expression.add("7");
            tts.speak("7", TextToSpeech.QUEUE_FLUSH, null);

        }
        if(Arrays.equals(intArray,num8))
        {
            System.out.println("8");
            expression.add("8");
            tts.speak("8", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num9))
        {
            System.out.println("9");
            expression.add("9");
            tts.speak("9", TextToSpeech.QUEUE_FLUSH, null);

        }
        if(Arrays.equals(intArray,num0))
        {
            System.out.println("0");
            expression.add("0");
            tts.speak("0", TextToSpeech.QUEUE_FLUSH, null);

        }


        if (Arrays.equals(intArray, a)) {
            if(!confirmName) {
                System.out.println("a");
                expression.add("a");
                tts.speak("a", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, b)) {
            if(!confirmName) {
                System.out.println("b");
                expression.add("b");
                tts.speak("b", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, c)) {
            if(!confirmName) {
                System.out.println("c");
                expression.add("c");
                tts.speak("c", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, d)) {
            if(!confirmName) {
                System.out.println("d");
                expression.add("d");
                tts.speak("d", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, e)) {
            if(!confirmName) {
                System.out.println("e");
                expression.add("e");
                tts.speak("e", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, f)) {
            if(!confirmName) {
                System.out.println("f");
                expression.add("f");
                tts.speak("f", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, g)) {
            if(!confirmName) {
                System.out.println("g");
                expression.add("g");
                tts.speak("g", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, h)) {
            if(!confirmName) {
                System.out.println("h");
                expression.add("h");
                tts.speak("h", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, i)) {
            if(!confirmName) {

                System.out.println("i");
                expression.add("i");
                tts.speak("i", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, j)) {
            if(!confirmName) {
                System.out.println("j");
                expression.add("j");
                tts.speak("j", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }

        if (Arrays.equals(intArray, k)) {
            if(!confirmName) {
                System.out.println("k");
                expression.add("k");
                tts.speak("k", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, l)) {
            if (!confirmName) {
                System.out.println("l");
                expression.add("l");
                tts.speak("l", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, m)) {
            if (!confirmName) {
                System.out.println("m");
                expression.add("m");
                tts.speak("m", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, n)) {
            if (!confirmName) {
                System.out.println("n");
                expression.add("n");
                tts.speak("n", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }


        }
        if (Arrays.equals(intArray, o)) {
            if (!confirmName) {
                System.out.println("o");
                expression.add("o");
                tts.speak("o", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, p)) {
            if (!confirmName) {

                System.out.println("p");
                expression.add("p");
                tts.speak("p", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if (Arrays.equals(intArray, q)) {
            if (!confirmName) {
                System.out.println("q");
                expression.add("q");
                tts.speak("q", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, r)) {
            if (!confirmName) {
                System.out.println("r");
                expression.add("r");
                tts.speak("r", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, s)) {
            if (!confirmName) {
                System.out.println("s");
                expression.add("s");
                tts.speak("s", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, t)) {
            if (!confirmName) {
                System.out.println("t");
                expression.add("t");
                tts.speak("t", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, u)) {
            if (!confirmName) {
                System.out.println("u");
                expression.add("u");
                tts.speak("u", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, v)) {
            if (!confirmName) {
                System.out.println("v");
                expression.add("v");
                tts.speak("v", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, w)) {
            if (!confirmName) {
                System.out.println("w");
                expression.add("w");
                tts.speak("w", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, x)) {
            if (!confirmName) {
                System.out.println("x");
                expression.add("x");
                tts.speak("x", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, y)) {
            if (!confirmName) {
                System.out.println("y");
                expression.add("y");
                tts.speak("y", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (Arrays.equals(intArray, z)) {
            if(!confirmName){
                System.out.println("z");
                expression.add("z");
                tts.speak("z", TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                System.out.println("Invalid sign");
                tts.speak("Invalid sign.Please enter contact number correctly", TextToSpeech.QUEUE_FLUSH, null);
            }

        }
        if(!Arrays.equals(intArray, num1) && !Arrays.equals(intArray, num2) && !Arrays.equals(intArray, num3) && !Arrays.equals(intArray, num5) && !Arrays.equals(intArray, num6) && !Arrays.equals(intArray, num4) && !Arrays.equals(intArray, num7) && !Arrays.equals(intArray, num8) && !Arrays.equals(intArray, num9)&& !Arrays.equals(intArray, num0)&& !Arrays.equals(intArray, a)&& !Arrays.equals(intArray, b)&& !Arrays.equals(intArray, c)&& !Arrays.equals(intArray, d)&& !Arrays.equals(intArray, e)&& !Arrays.equals(intArray, f)&& !Arrays.equals(intArray, g)&& !Arrays.equals(intArray, h)&& !Arrays.equals(intArray, i)&& !Arrays.equals(intArray, j)&& !Arrays.equals(intArray, k)&& !Arrays.equals(intArray, l)&& !Arrays.equals(intArray, m)&& !Arrays.equals(intArray, n)&& !Arrays.equals(intArray, o)&& !Arrays.equals(intArray, p)&& !Arrays.equals(intArray, q)&& !Arrays.equals(intArray, r)&& !Arrays.equals(intArray, s)&& !Arrays.equals(intArray, t)&& !Arrays.equals(intArray, u)&& !Arrays.equals(intArray, v)&& !Arrays.equals(intArray, w)&& !Arrays.equals(intArray, x)&& !Arrays.equals(intArray, y)&& !Arrays.equals(intArray, z))
        {
            System.out.println("invalid sign");
            tts.speak("Invalid sign.Please enter braile sign correctly", TextToSpeech.QUEUE_FLUSH, null);
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
        public boolean onFling(MotionEvent e1, MotionEvent e2,float velocityX, float velocityY) {
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
                    if(expression.size()==0)
                    {
                        speakWords("you have not entered any value.Please enter value ");
                    }
                    else
                    {

                        String listString = "";

                        for (String s : expression)
                        {
                            listString += s;
                        }

                        System.out.println(listString);
                        speakWords(listString);


                    }

                }
            } else {
                if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {

                    Intent intent = new Intent(AddContactDetails.this, ChangeDetails.class);
                    startActivity(intent);
                    onDestroy();

                    Log.d("Gesture ", " RightToLeft");


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " LeftToright");

                    if((ContactName =="") && (ContactNu =="")) {
                        String listName = "";


                        for (String name : expression) {
                            listName += name + "";
                        }
                        System.out.println(listName);
                        ContactName=listName.toString();
                        expression.clear();
                        if(ContactName!="") {
                            tts.speak("you have entered" + ContactName + "  as contact person's name.Please enter his or her contact number", TextToSpeech.QUEUE_FLUSH, null);
                            confirmName = true;

                        }
                        else
                        {
                            tts.speak("Please enter Contact person's name", TextToSpeech.QUEUE_FLUSH, null);
                        }

                        // ContactName="";
                    }
                    else  if((ContactName !="") && (ContactNu ==""))
                    {
                        String listName = "";
                        confirmNumber=true;

                        Integer noCount;
                        noCount = expression.size();
                        if(noCount.equals(0)) {
                            tts.speak("you have not entered " + ContactName + "'s number.Please enter  his or her Contact number", TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if(noCount==10)
                        {
                            for (String name : expression) {
                                listName += name + "";
                            }
                            System.out.println(listName);
                            ContactNu=listName.toString();
                            expression.clear();
                            if(ContactNu=="")
                            {
                                tts.speak("you have not entered "+ContactName+"'s number.Please enter Contact his or her number", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else
                            {
                                tts.speak("you have  entered "+ContactName+"'s number as "+ContactNu+". Please swap left to right if you want to confirm these details", TextToSpeech.QUEUE_FLUSH, null);
                                confirmName = false;
                            }


                        }
                        else
                        {
                            tts.speak("Invalid contact number.Please enter Contact his or her number correctly", TextToSpeech.QUEUE_FLUSH, null);

                        }






                    }
                    else if ((ContactName !="") && (ContactNu !=""))
                    {

                        System.out.println(ContactName+"AND"+ContactNu);


                        speakWords("Saving contact number of "+ContactName+" as "+ContactNu+".");

                        ArrayList<contacts> results = sharedPreference.getContacts(AddContactDetails.this);
                        int count;
                        int prNumber;
                        if(results==null)
                        {
                            count=0;
                            prNumber=1;

                        }
                        else {
                            int[] totalCount=new int[]{1,2,3,4,5,6,7,8,9};


                            for (int i=0; i<results.size(); i++) {

                                arrPrnumbers[i]=Integer.valueOf(results.get(i).getPr_no());
                            }


                            remainPrnumbers= difference(arrPrnumbers,totalCount);
                            Arrays.sort(remainPrnumbers);
                            prNumber=remainPrnumbers[0];
                            System.out.println(prNumber);



                            //count = results.size();
                        }
                        // int prNumber=count+1;
                        contacts contact=new contacts(String.valueOf(prNumber),ContactNu.toString(),ContactName.toString());
                        sharedPreference.addContacts(getBaseContext(),contact);

                        speakWords("You have successfully saved the contact details .Priority number of "+ContactName+" is "+String.valueOf(prNumber)+"");

                        ContactName="";
                        ContactNu="";
                        expression.clear();

                        speakWords("If you like to add another contact details please swap left to right .");

                    }




                }
            }

            return true;

        }


    }

    public static int[] difference( int[] a, int[] b ) {
        HashSet<Integer> res = new HashSet<>();
        Arrays.sort( a );
        for ( int elem : b ) {
            if ( !res.contains( elem ) && Arrays.binarySearch( a, elem ) < 0 )
                res.add( elem );
        }
        int[] ar = new int[ res.size() ];
        int i = 0;
        for ( int elem : res )
            ar[ i++ ] = elem;
        return ar;
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
}
