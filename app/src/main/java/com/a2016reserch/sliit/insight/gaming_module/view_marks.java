package com.a2016reserch.sliit.insight.gaming_module;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a2016reserch.sliit.insight.R;
import com.a2016reserch.sliit.insight.gaming_module.Logic.DBHelper;
import com.a2016reserch.sliit.insight.gaming_module.Logic.QuestionUpdateEndpoint;
import com.a2016reserch.sliit.insight.gaming_module.Logic.Questions;
import com.a2016reserch.sliit.insight.gaming_module.Logic.Update_Question;
import com.a2016reserch.sliit.insight.learning_module.help_keypad.Braille_Tutorials;
import com.a2016reserch.sliit.insight.learning_module.help_keypad.MainMenu_LearningModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class view_marks extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    TextView markresult;
    ImageView image;
    String data = null;
    int finalResult, count = 0;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_marks);

        // Android Gestures
        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector();
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        tts = new TextToSpeech(this, this);

        image = (ImageView) findViewById(R.id.imageView);
        markresult = (TextView) findViewById(R.id.textView6);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("marks");
        int[] array1 = extras.getIntArray("array");

        System.out.print(data);
        System.out.print(array1);

        List<Integer> intList = new ArrayList<Integer>(array1.length);

        for (int i = 0; i < array1.length; i++) {
            intList.add(array1[i]);
        }
        correcrtAnswerVoice(intList);

        finalResult = Integer.parseInt(data);

        if (finalResult > 0) {
            count = finalResult / 10;
        }


        final Thread logoTimer3 = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    speakWords("Sorry. You have given incorrect answer. Game is over");
                    sleep(2000);
                    speakWords("you have answered" + count + "questions");
                    sleep(2000);
                    speakWords("Your got" + finalResult + "marks");
                    sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer3.start();

        VoiceResult();
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
            Log.e("TTS", "Initialization Failed!");
        }
    }

    private void speakWords(String speech) {

        // speak straight away
        if (tts != null) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        while (tts.isSpeaking()) {

        }
    }

    public void VoiceResult() {
        if (finalResult == 0) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);

            Thread logoTimer4 = new Thread() {
                public void run() {
                    try {
                        sleep(10000);
                        speakWords("You fall in the category of Feeble-mindedness");
                        sleep(10000);
                        speakWords("If you want to go to gaming module main menu swipe the screen from right to left");
                        sleep(4000);
                        speakWords("If you want to go to gaming levels main menu swipe the screen from left to right");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer4.start();


        } else if (finalResult >= 0 && finalResult < 20) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);
            Thread logoTimer5 = new Thread() {
                public void run() {
                    try {
                        sleep(10000);
                        speakWords("You fall in the category of Borderline deficiency in intelligence");
                        sleep(10000);
                        speakWords("If you want to go to gaming module main menu swipe the screen from right to left");
                        sleep(4000);
                        speakWords("If you want to go to gaming levels main menu swipe the screen from left to right");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer5.start();

        } else if (finalResult >= 20 && finalResult < 40) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);

            Thread logoTimer6 = new Thread() {
                public void run() {
                    try {
                        sleep(10000);
                        speakWords("You fall in the category of Dullness");
                        sleep(10000);
                        speakWords("If you want to go to gaming module main menu swipe the screen from right to left");
                        sleep(4000);
                        speakWords("If you want to go to gaming levels main menu swipe the screen from left to right");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer6.start();

        } else if (finalResult >= 40 && finalResult < 50) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);
            Thread logoTimer7 = new Thread() {
                public void run() {
                    try {
                        sleep(10000);
                        speakWords("You fall in the category of Average");
                        sleep(10000);
                        speakWords("If you want to go to gaming module main menu swipe the screen from right to left");
                        sleep(4000);
                        speakWords("If you want to go to gaming levels main menu swipe the screen from left to right");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer7.start();

        } else if (finalResult >= 50 && finalResult < 70) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setImageResource(R.drawable.happy_face);
            Thread logoTimer8 = new Thread() {
                public void run() {
                    try {
                        sleep(10000);
                        speakWords("You fall in the category of Superior intelligence");
                        sleep(10000);
                        speakWords("If you want to go to gaming module main menu swipe the screen from right to left");
                        sleep(4000);
                        speakWords("If you want to go to gaming levels main menu swipe the screen from left to right");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer8.start();

        } else if (finalResult >= 70 && finalResult < 90) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setImageResource(R.drawable.happy_face);
            Thread logoTimer9 = new Thread() {
                public void run() {
                    try {
//
                        sleep(10000);
                        speakWords("You fall in the category of Very superior intelligence");
                        sleep(10000);
                        speakWords("If you want to go to gaming module main menu swipe the screen from right to left");
                        sleep(4000);
                        speakWords("If you want to go to gaming levels main menu swipe the screen from left to right");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer9.start();

        } else {

            String val = finalResult + "";
            markresult.setText(val);
            image.setImageResource(R.drawable.more_happy_face);
            Thread logoTimer10 = new Thread() {
                public void run() {
                    try {
                        sleep(10000);
                        speakWords("You fall in the category of Genius");
                        sleep(10000);
                        speakWords("If you want to go to gaming module main menu swipe the screen from right to left");
                        sleep(4000);
                        speakWords("If you want to go to gaming levels main menu swipe the screen from left to right");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer10.start();

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);

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

                    Intent intent = new Intent(view_marks.this, MainMenu_GamingModule.class);
                    startActivity(intent);
                    onDestroy();


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {


                    Log.d("Gesture ", " LeftToright");
                    Intent intent = new Intent(view_marks.this, MainMenu_GameLevels.class);
                    startActivity(intent);
                    onDestroy();
                }
            }
            return true;

        }
    }


    public void correcrtAnswerVoice(List<Integer> number) {

        //      List<Integer> list1 = new ArrayList<Integer>(number);

        int[] intArray = new int[number.size()];

        for (int i = 0; i < number.size(); i++) {
            intArray[i] = number.get(i);
        }

        if (Arrays.asList(intArray).contains(10)) {
            String status = "correct";
            speakWords("Correct answer is letter A");
        } else {
            speakWords("invalid");
        }

    }


}
