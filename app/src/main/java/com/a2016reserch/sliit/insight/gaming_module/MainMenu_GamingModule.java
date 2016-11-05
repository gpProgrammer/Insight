package com.a2016reserch.sliit.insight.gaming_module;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.a2016reserch.sliit.insight.R;
import com.a2016reserch.sliit.insight.gaming_module.Logic.Questions;
import com.a2016reserch.sliit.insight.gaming_module.Logic.QuestionUpdateEndpoint;
import com.a2016reserch.sliit.insight.gaming_module.Logic.DBHelper;
import com.a2016reserch.sliit.insight.gaming_module.Logic.Update_Question;
import com.a2016reserch.sliit.insight.learning_module.help_keypad.Braille_Keypad;
import com.a2016reserch.sliit.insight.learning_module.help_keypad.Braille_Tutorials;
import com.a2016reserch.sliit.insight.learning_module.help_keypad.Intro_Keypad_Top;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMenu_GamingModule extends Activity implements TextToSpeech.OnInitListener {

    private int start = -1;  // main items counter variable
    private TextView name; // text view variable
    private String[] options = new String[]{"Intro", "keypad", "Tutor"}; // String array for components
    private GestureDetector mGestureDetector;
    private TextToSpeech tts; // text to speech variable
    // This code can be any value you want, its just a checksum.
    private static final int MY_DATA_CHECK_CODE = 1234;
    public static String lastCommand = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu__gaming_module);

        // Android Gestures
        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector();
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        // Fire off an intent to check if a TTS engine is installed
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        // change text view value
        name = (TextView) findViewById(R.id.txtName);
        name.setText("Welcome to Gaming Module");

        Thread logoTimer = new Thread() {
            public void run() {
                try {

                    sleep(1500);
                    speakWords("Welcome to Gaming Module");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    /**
     * Called when another activity is taking focus.
     */
    @Override
    protected void onRestart() {

        if (tts == null) {
            // success, create the TTS instance
            tts = new TextToSpeech(this, this);
        }

        super.onRestart();
    }

    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        if (tts == null) {
            // success, create the TTS instance
            tts = new TextToSpeech(this, this);
        }

        super.onStart();
    }

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {

        // tts shutdown!
        if (tts != null) {
            tts.stop();
            // tts.shutdown();
        }
        super.onStop();

    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {

        // tts shutdown!
        if (tts != null) {
            tts.stop();
            //tts.shutdown();
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

    private void speakWords(String speech) {

        // speak straight away
        if (tts != null) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        while (tts.isSpeaking()) {

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
            Thread logoTimer = new Thread() {
                public void run() {
                    try {

                        sleep(1000);
                        if (lastCommand.equalsIgnoreCase("Exit!")) {
                            speakWords("app is restarted!");
                        } else {

                            speakWords(lastCommand);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer.start();
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("Gesture ", " onShowPress");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("Gesture ", " onDoubleTap");
            int val = start;
            if (start == 0) {
                speakWords("Start Game");
                Intent intent = new Intent(MainMenu_GamingModule.this, view_questions.class);
                startActivity(intent);
                onDestroy();

            }
            if (start == -1) {
                //  view_key_pad();

            } else if (start == 1) {

                speakWords("Update Game");
                speakWords("Wait a moment, game will be started in few minutes");
//                Intent intent = new Intent(MainMenu_GamingModule.this, Braille_Keypad.class);
//                startActivity(intent);
//                onDestroy();

                Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
                Retrofit retrofit = retrofitBuilder.baseUrl("http://192.168.2.10:8084/insight/webapi/").addConverterFactory(GsonConverterFactory.create()).build();

                final QuestionUpdateEndpoint updateQues = retrofit.create(QuestionUpdateEndpoint.class);

                Call<ArrayList<Update_Question>> getUserLoginCall = updateQues.getQues();
                getUserLoginCall.enqueue(new Callback<ArrayList<Update_Question>>() {

                    @Override
                    public void onResponse(Call<ArrayList<Update_Question>> call, Response<ArrayList<Update_Question>> response) {

                        DBHelper helper = new DBHelper(MainMenu_GamingModule.this);
                        helper.open();

                        helper.clearTable();

                        for (int i = 0; i < response.body().size(); i++) {

                            Questions newQuz = new Questions();
                            newQuz.setQuizId(response.body().get(i).getQuzId());
                            newQuz.setAnswers(response.body().get(i).getAnswer());
                            newQuz.setQuestion(response.body().get(i).getQuestion());
                            newQuz.setDifficulty(response.body().get(i).getLevel());
                            newQuz.setSequence(response.body().get(i).getSequence());
                            helper.insertQuestions(newQuz);
                        }
                        helper.close();
                        speakWords("Questions have Updated successfully");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Update_Question>> call, Throwable t) {
                        Toast.makeText(MainMenu_GamingModule.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainMenu_GamingModule.this, "server error", Toast.LENGTH_SHORT).show();
                    }
                });


            } else if (start == 2) {
                speakWords("Instructions");
                Intent intent = new Intent(MainMenu_GamingModule.this, Braille_Tutorials.class);
                startActivity(intent);
                onDestroy();

            } else {
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

                    start = start + 1;

                    for (int i = start; i <= options.length + 1; i++) {

                        if (start > 2) {

                            System.out.println(options[0]);
                            viewText(0);
                            start = 0;

                        } else {
                            System.out.println(options[(start)]);
                            viewText(start);
                            //  start=start+1;

                        }

                        break;
                    }


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {

                    start = start - 1;
                    for (int i = start; i >= -2; i--) {
                        if (start <= -1) {
                            start = 2;
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

    public void viewText(int no) {
        if (no == 0) {
            name.setText("Start Game");
            speakWords("Start Game");

        } else if (no == 1) {
            name.setText("Update Game");
            speakWords("Update Game");

        } else if (no == 2) {
            name.setText("Instructions");
            speakWords("Instructions");
        }


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
