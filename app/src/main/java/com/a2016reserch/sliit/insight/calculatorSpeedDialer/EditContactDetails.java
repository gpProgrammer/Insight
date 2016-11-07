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

public class EditContactDetails extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    SharedPreference sharedPreference;
    private GestureDetector mGestureDetector;
    ArrayList<String> expression = new ArrayList<String>();
    boolean isStarted=false;
    ArrayList<contacts> getAllprnu;
    int no=-1;
    boolean confirmEdit=false;
    boolean numberEntered=false;
    int count=0;
    boolean contactName=false;
    boolean prNumber=false;
    boolean contactNumber=false;
    boolean savedata=false;
    String newName="";
    String newNumber="";
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
        setContentView(R.layout.activity_edit_contact_details);

        tts = new TextToSpeech(this, this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("Please enter priority number to edit details.", TextToSpeech.QUEUE_FLUSH, null);
                prNumber=true;

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
                            Toast.makeText(EditContactDetails.this, ids, Toast.LENGTH_LONG).show();

                            getSign(mClickedButtonIds);
                            mClickedButtonIds.clear();


                        }
                    },
                    3000);

        } else {
            mClickedButtonIds.add(v.getId());
        }
    }


    public void instructions()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("Please enter priority number to edit details.", TextToSpeech.QUEUE_FLUSH, null);
                prNumber=true;

                // tts.speak("First enter contact persons name", TextToSpeech.QUEUE_FLUSH, null);

                //speak after 1000ms
            }
        }, 1000);


    }



    public void getSign(List<Integer> number)
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


        if(Arrays.equals(intArray,num1))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);

            if(prNumber) {
                confirmEdit = true;
                no = getIndex("1");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("1");
                speakWords("1.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("1");
                speakWords("1");
            }

        }
        if(Arrays.equals(intArray,num2))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("2");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("2");
                speakWords("1.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("2");
                speakWords("2");
            }
        }
        if(Arrays.equals(intArray,num3))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("3");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("3");
                speakWords("1.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("3");
                speakWords("3");
            }
        }
        if(Arrays.equals(intArray,num4))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("4");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("4");
                speakWords("4.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("4");
                speakWords("4");
            }

        }
        if(Arrays.equals(intArray,num5))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("5");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("5");
                speakWords("5.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("5");
                speakWords("5");
            }
        }
        if(Arrays.equals(intArray,num6))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("6");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("6");
                speakWords("6.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("6");
                speakWords("6");
            }
        }
        if(Arrays.equals(intArray,num7))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("7");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("7");
                speakWords("7.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("7");
                speakWords("7");
            }
        }
        if(Arrays.equals(intArray,num8))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("8");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("8");
                speakWords("8.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("8");
                speakWords("8");
            }
        }
        if(Arrays.equals(intArray,num9))
        {
            SharedPreference sharedPreference=new SharedPreference();
            getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
            if(prNumber) {
                confirmEdit = true;
                no = getIndex("9");
                if (no != -1) {
                    tts.speak("Dou You want to edit" + getAllprnu.get(no).getCon_name() + "Contact details,if yes swap left to right", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("There's no number assigned to this priority number", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            if(contactName)
            {
                expression.add("9");
                speakWords("9.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("9");
                speakWords("9");
            }
        }
        if (Arrays.equals(intArray, num0)) {

            System.out.println("0");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("0");
                speakWords("0.swap right to confirm new name");
            }
            if(contactNumber)
            {
                expression.add("0");
                speakWords("0");
            }

        }

        if (Arrays.equals(intArray, a)) {

            System.out.println("a");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("a");
                speakWords("a");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, b)) {

            System.out.println("b");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("b");
                speakWords("b");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, c)) {

            System.out.println("c");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("c");
                speakWords("c");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, d)) {

            System.out.println("d");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("d");
                speakWords("d");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, e)) {

            System.out.println("e");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("e");
                speakWords("e");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, f)) {

            System.out.println("f");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("f");
                speakWords("f");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, g)) {

            System.out.println("g");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("g");
                speakWords("g");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, h)) {

            System.out.println("h");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("h");
                speakWords("h");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, i)) {

            System.out.println("i");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("i");
                speakWords("i");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, j)) {

            System.out.println("j");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("j");
                speakWords("j");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, k)) {

            System.out.println("k");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("k");
                speakWords("k");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, l)) {

            System.out.println("l");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("l");
                speakWords("l");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, m)) {

            System.out.println("m");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("m");
                speakWords("m");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, n)) {

            System.out.println("n");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("n");
                speakWords("n");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, o)) {

            System.out.println("o");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("o");
                speakWords("o");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, p)) {

            System.out.println("p");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("p");
                speakWords("p");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, q)) {

            System.out.println("q");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("q");
                speakWords("q");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, r)) {

            System.out.println("r");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("r");
                speakWords("r");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, s)) {

            System.out.println("s");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("s");
                speakWords("s");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, t)) {

            System.out.println("t");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("t");
                speakWords("t");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, u)) {

            System.out.println("u");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("u");
                speakWords("u");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, v)) {

            System.out.println("v");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("v");
                speakWords("v");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, w)) {

            System.out.println("w");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("w");
                speakWords("w");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, x)) {

            System.out.println("x");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("x");
                speakWords("x");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, y)) {

            System.out.println("y");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("y");
                speakWords("y");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }
        if (Arrays.equals(intArray, z)) {

            System.out.println("z");
            if(prNumber) {
                speakWords("invalid sign please enter priority number correctly");
            }
            if(contactName)
            {
                expression.add("z");
                speakWords("z");
            }
            if(contactNumber)
            {

                speakWords("invalid sign please enter Contact number correctly");
            }

        }

        if(!Arrays.equals(intArray, num1) && !Arrays.equals(intArray, num2) && !Arrays.equals(intArray, num3) && !Arrays.equals(intArray, num5) && !Arrays.equals(intArray, num6) && !Arrays.equals(intArray, num4) && !Arrays.equals(intArray, num7) && !Arrays.equals(intArray, num8) && !Arrays.equals(intArray, num9)&& !Arrays.equals(intArray, num0)&& !Arrays.equals(intArray, a)&& !Arrays.equals(intArray, b)&& !Arrays.equals(intArray, c)&& !Arrays.equals(intArray, d)&& !Arrays.equals(intArray, e)&& !Arrays.equals(intArray, f)&& !Arrays.equals(intArray, g)&& !Arrays.equals(intArray, h)&& !Arrays.equals(intArray, i)&& !Arrays.equals(intArray, j)&& !Arrays.equals(intArray, k)&& !Arrays.equals(intArray, l)&& !Arrays.equals(intArray, m)&& !Arrays.equals(intArray, n)&& !Arrays.equals(intArray, o)&& !Arrays.equals(intArray, p)&& !Arrays.equals(intArray, q)&& !Arrays.equals(intArray, r)&& !Arrays.equals(intArray, s)&& !Arrays.equals(intArray, t)&& !Arrays.equals(intArray, u)&& !Arrays.equals(intArray, v)&& !Arrays.equals(intArray, w)&& !Arrays.equals(intArray, x)&& !Arrays.equals(intArray, y)&& !Arrays.equals(intArray, z))
        {
            System.out.println("invalid sign");
            tts.speak("Invalid sign.Please enter braile sign correctly", TextToSpeech.QUEUE_FLUSH, null);
        }



    }


    public int getIndex(String no)
    {
        SharedPreference sharedPreference=new SharedPreference();
        ArrayList<contacts> results=sharedPreference.getContacts(EditContactDetails.this);

        for (int i=0; i<results.size(); i++) {
            if(results.get(i).getPr_no().contentEquals(no))
            {
                return i;
            }
        }

        return -1;

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
                        speakWords("removed last element which is  "+value);
                    }


                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " topToBottom");
                }
            } else {
                if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {

                    Intent intent = new Intent(EditContactDetails.this, ChangeDetails.class);
                    startActivity(intent);
                    onDestroy();

                    Log.d("Gesture ", " RightToLeft");


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " LeftToright");


                    count=count+1;

                    if(confirmEdit && count==1)
                    {
                        confirmEdit=false;
                        prNumber=false;
                        SharedPreference sharedPreference=new SharedPreference();
                        getAllprnu=sharedPreference.getContacts(EditContactDetails.this);
                        speakWords("Do you want to edit contact name."+getAllprnu.get(no).getCon_name()+"if yes please enter name, if you want to skip Swap right");
                        contactName=true;

                    }
                    else  if(expression.size()==0 && contactName && count==2)
                    {

                        speakWords("No change in contact name.please enter contact number ,if you want to skip Swap right");
                        expression.clear();
                        newName="";
                        contactName=false;
                        contactNumber=true;

                    }
                    else if(expression.size()!=0 && contactName && count==2)
                    {
                        String listName = "";
                        for (String name : expression) {
                            listName += name + "";
                        }
                        newName = listName.toString();
                        speakWords("New contact name" + newName + "swap right to edit contact number. if you want to skip entering number swap left to right");
                        expression.clear();
                        contactName=false;
                        contactNumber=true;

                    }
                    else if(expression.size()==0 && contactNumber && count==3)
                    {

                        speakWords("No change in contact number.Please swap left to right if you want to confirm these details");
                        expression.clear();
                        newNumber="";
                        contactNumber=false;
                        savedata=true;

                    }
                    else if(expression.size()!=0 && contactNumber && count==3)
                    {
                        if(expression.size()==10)
                        {
                            String sign= "";
                            for (String name : expression) {
                                sign += name + "";
                            }
                            newNumber=sign.toString();
                            expression.clear();
                            speakWords("you have  entered new number as "+newNumber+".Please swap left to right if you want to confirm these details");
                            contactNumber=false;
                            savedata=true;

                        }
                        else
                        {
                            speakWords("Invalid contact number.Please enter Contact his or her number correctly,if you want to skip number and confirm details swap left to right");
                            expression.clear();
                            count=2;
                            contactNumber=true;
                            savedata=false;
                        }


                    }
                    else if(savedata && count==4)
                    {
                        if(newName=="" && newNumber=="")
                        {
                            System.out.println("You have not changed any details");
                            SharedPreference sharedPreference=new SharedPreference();
                            ArrayList<contacts> results=sharedPreference.getContacts(EditContactDetails.this);
                            speakWords("You have not changed any details.So priority number "+results.get(no).getPr_no()+"Contact name is"+results.get(no).getCon_name()+" and Contact number is"+results.get(no).getCon_no()+"");
                            savedata=false;
                            expression.clear();
                            count=0;
                            no=-1;
                            instructions();

                        }
                        else if(newName!=""&&newNumber=="")
                        {
                            System.out.println("You changed only name");
                            SharedPreference sharedPreference=new SharedPreference();
                            ArrayList<contacts> results=sharedPreference.getContacts(EditContactDetails.this);
                            speakWords("You have only changed name.So priority number "+results.get(no).getPr_no().toString()+"Contact name is"+newName+" and Contact number is"+results.get(no).getCon_no()+"");
                            contacts contact=new contacts(results.get(no).getPr_no().toString(),results.get(no).getCon_no().toString(),newName.toString());
                            sharedPreference.editContacts(getBaseContext(), contact, no);
                            savedata=false;
                            expression.clear();
                            count=0;
                            no=-1;
                            newName="";
                            instructions();
                        }
                        else if(newName=="" && newNumber!="")
                        {
                            System.out.println("You changed only number");
                            SharedPreference sharedPreference=new SharedPreference();
                            ArrayList<contacts> results=sharedPreference.getContacts(EditContactDetails.this);
                            speakWords("You have only changed number.So priority number "+results.get(no).getPr_no().toString()+"Contact name is"+results.get(no).getCon_name().toString()+" and Contact number is"+newNumber+"");
                            contacts contact=new contacts(results.get(no).getPr_no().toString(),newNumber,results.get(no).getCon_name().toString());
                            sharedPreference.editContacts(getBaseContext(), contact, no);
                            savedata=false;
                            expression.clear();
                            count=0;
                            no=-1;
                            newNumber="";
                            instructions();
                        }
                        else if(newName!=""&&newNumber!="")
                        {
                            System.out.println("You changed both number and name");
                            SharedPreference sharedPreference=new SharedPreference();
                            ArrayList<contacts> results=sharedPreference.getContacts(EditContactDetails.this);
                            speakWords("You have  changed both number and name.So priority number "+results.get(no).getPr_no().toString()+"Contact name is"+newName.toString()+" and Contact number is"+newNumber+"");
                            contacts contact=new contacts(results.get(no).getPr_no().toString(),newNumber,newName);
                            sharedPreference.editContacts(getBaseContext(), contact, no);
                            savedata=false;
                            expression.clear();
                            count=0;
                            no=-1;
                            newName="";
                            newNumber="";
                            instructions();


                        }


                    }
                    else
                    {
                        count=0;
                    }






                }
            }
            return true;

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



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
        // Return true if you have consumed the event, false if you haven't.
        // The default implementation always returns false.
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
