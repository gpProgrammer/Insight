package com.a2016reserch.sliit.insight.calculatorSpeedDialer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.a2016reserch.sliit.insight.InsightHomeActivity;
import com.a2016reserch.sliit.insight.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Calculator_Module extends Activity implements TextToSpeech.OnInitListener {

    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    private TextToSpeech tts;
    ArrayList<String> expression = new ArrayList<String>();
    Vibrator N1,N2,N3,N4,N5,N6,N7,N8,N9,N0,ans;
    boolean isStarted=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
// Create a GestureDetector
        mGestureDetector = new GestureDetector(this, android_gesture_detector);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calculator__module);

        tts = new TextToSpeech(this,this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("You are in Calculator forum.Please enter your problem", TextToSpeech.QUEUE_FLUSH, null);
                // tts.speak("First enter contact persons name", TextToSpeech.QUEUE_FLUSH, null);

                //speak after 1000ms
            }
        }, 1000);
    }

    public void onClick(View v) {
        AudioManager audioManager =
                (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN,1000.0f);
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

                            getSigns(mClickedButtonIds);
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

    public  void getSigns(List<Integer> sign)
    {



        int[] intArray = new int[sign.size()];
        for (int i = 0; i < sign.size(); i++) {
            intArray[i] = sign.get(i);
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
        int[] plus = new int[] {R.id.button2,R.id.button5,R.id.button6};
        int[] minus = new int[] {R.id.button5,R.id.button6};
        int[] div = new int[] {R.id.button2,R.id.button2,R.id.button5,R.id.button6};
        int[] mul = new int[] {R.id.button1,R.id.button2,R.id.button6};
        int[] eq = new int[] {R.id.button1,R.id.button2,R.id.button5,R.id.button6};
        int[] decimal= new int[] {R.id.button2,R.id.button6};
        int[] opbr= new int[] {R.id.button1,R.id.button3,R.id.button4,R.id.button5,R.id.button6};
        int[] clbr= new int[] {R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6};


        Arrays.sort(intArray);


        //speakOut();


        if (intArray == null){
            System.out.println("Please enter the number");

        }
        if(Arrays.equals(intArray,num1))
        {
            System.out.println("call 1");
            expression.add("1");
            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,
            };

            N1= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N1.vibrate(pattern, -1);
            tts.speak("1", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num2))
        {
            System.out.println("call 2");
            expression.add("2");
            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot
            };

            N2= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N2.vibrate(pattern, -1);
            tts.speak("2", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num3))
        {
            System.out.println("3");
            expression.add("3");
            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot,short_gap,dot
            };

            N3= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N3.vibrate(pattern, -1);

            tts.speak("3", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num4))
        {
            System.out.println("4");
            expression.add("4");

            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot,short_gap,dot,short_gap,dot
            };

            N4= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N4.vibrate(pattern, -1);

            tts.speak("4", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num5))
        {
            System.out.println("5");
            expression.add("5");

            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot
            };

            N5= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N5.vibrate(pattern, -1);
            tts.speak("5", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num6))
        {
            System.out.println("6");
            expression.add("6");

            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot
            };

            N6= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N6.vibrate(pattern, -1);
            tts.speak("6", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num7))
        {
            System.out.println("7");
            expression.add("7");

            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot
            };

            N7= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N7.vibrate(pattern, -1);
            tts.speak("7", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num8))
        {
            System.out.println("8");
            expression.add("8");

            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot
            };

            N8= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N8.vibrate(pattern, -1);
            tts.speak("8", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num9))
        {
            System.out.println("9");
            expression.add("9");
            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot,short_gap,dot
            };

            N9= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N9.vibrate(pattern, -1);
            tts.speak("9", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,num0))
        {
            System.out.println("0");
            expression.add("0");
            int dot = 200;
            int dash = 500;
            int short_gap = 200;
            int medium_gap = 500;
            int long_gap = 1000;
            long[] pattern = {
                    0,  // Start immediately
                    medium_gap
            };

            N0= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            N0.vibrate(pattern, -1);
            tts.speak("0", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,decimal))
        {
            System.out.println(".");
            expression.add(".");
            tts.speak("point", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,plus))
        {
            System.out.println("+");
            expression.add("+");
            tts.speak("+", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,minus))
        {
            System.out.println("-");
            expression.add("-");
            tts.speak("minus", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,div))
        {
            System.out.println("/");
            expression.add("/");
            tts.speak("divide", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,mul))
        {
            System.out.println("*");
            expression.add("*");
            tts.speak("multiply", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,opbr))
        {
            System.out.println("(");
            expression.add("(");
            tts.speak("open bracket", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,clbr))
        {
            System.out.println(")");
            expression.add(")");
            tts.speak("close bracket", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(Arrays.equals(intArray,eq))
        {
            // System.out.println("=");
            // tts.speak(, TextToSpeech.QUEUE_FLUSH, null);

            //speakWords("equals");

            if(expression.isEmpty())
            {
                speakWords("please enter your problem");
                expression.clear();
            }
            else {
                String listString = "";

                for (String s : expression) {
                    listString += s + "";
                }
                System.out.println(listString);
                //String a=listString;

                System.out.println(eval(listString));

                double answer = eval(listString);
                if (answer < 0) {
                    System.out.println("0");
                    expression.add("0");
                    int dot = 200;
                    int dash = 500;
                    int short_gap = 200;
                    int medium_gap = 500;
                    int long_gap = 1000;
                    long[] pattern = {
                            0,  // Start immediately
                            long_gap
                    };

                    ans = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    ans.vibrate(pattern, -1);
                    tts.speak("0", TextToSpeech.QUEUE_FLUSH, null);
                    speakWords(listString + " equals minus " + String.valueOf(eval(listString)));
                    expression.clear();
                } else {
                    System.out.println("0");
                    expression.add("0");
                    int dot = 200;
                    int dash = 500;
                    int short_gap = 200;
                    int medium_gap = 500;
                    int long_gap = 1000;
                    long[] pattern = {
                            0,  // Start immediately
                            long_gap
                    };

                    ans = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    ans.vibrate(pattern, -1);
                    tts.speak("0", TextToSpeech.QUEUE_FLUSH, null);
                    speakWords(listString + " equals " + String.valueOf(eval(listString)));
                    expression.clear();
                }

            }





        }


        if(!Arrays.equals(intArray, num1) && !Arrays.equals(intArray, num2) && !Arrays.equals(intArray, num3) && !Arrays.equals(intArray, num5) && !Arrays.equals(intArray, num6) && !Arrays.equals(intArray, num4) && !Arrays.equals(intArray, num7) && !Arrays.equals(intArray, num8) && !Arrays.equals(intArray, num9)&& !Arrays.equals(intArray, opbr) && !Arrays.equals(intArray, clbr) && !Arrays.equals(intArray, plus) && !Arrays.equals(intArray, minus) && !Arrays.equals(intArray, div) && !Arrays.equals(intArray, mul) && !Arrays.equals(intArray, decimal) && !Arrays.equals(intArray, num0) && !Arrays.equals(intArray, eq))
        {
            System.out.println("invalid sign");
            speakWords("Invalid sign.Please enter braille sign correctly");
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

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
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

                    Intent intent = new Intent(Calculator_Module.this, InsightHomeActivity.class);
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
       /* if (flingDetector.onTouchEvent(event)) {
            return true;
        }*/
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);

        // return super.onTouchEvent(event);


    }
}
