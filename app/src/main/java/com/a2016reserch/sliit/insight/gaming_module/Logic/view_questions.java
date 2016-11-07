package com.a2016reserch.sliit.insight.gaming_module.Logic;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.a2016reserch.sliit.insight.R;
import com.a2016reserch.sliit.insight.gaming_module.view_marks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class view_questions extends Activity implements  TextToSpeech.OnInitListener, View.OnClickListener {

    private DBHelper dbClass;
    String type=null;
    private TextToSpeech tts;
    private GestureDetector mGestureDetector;

    String passedArg=null;
    int qid;
    int seq=0;
    int marks=0;
    String status=null;
    boolean isStarted=false;
    private List<Integer> mClickedButtonIds = new ArrayList<Integer>();
    private List<Marks> m = new ArrayList<Marks>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);

        dbClass = new DBHelper(this);


        Questions quz_One_Easy = new Questions(1, "What is the fist letter of word Ball?","easy","10,30",1);
        Questions quz_Two_Easy =new Questions(2, "Mark the dots corresponding to letter D?","easy","10,20,40",1);
        Questions quz_Three_Easy =new Questions(3, "Mark the dots corresponding to number 3?","easy","10,20",1);
        Questions quz_Four_Easy =new Questions(4, "Mark the dots corresponding to comma?","easy","10",1);
        Questions quz_Five_Easy =new Questions(5, "How many points do you use for showing braille?","easy","10,20",1);
        Questions quz_Six_Easy =new Questions(6, "What is the first letter of word ice cream?","easy","10,30,50",1);
        Questions quz_Seven_Easy =new Questions(7, "What is the result of calculation 1+8?","easy","20,30",1);
        Questions quz_Eight_Easy =new Questions(8, "What is the missing letter word Mango. M,A,N,G,blank?","easy","10,40,50",1);
        Questions quz_Nine_Easy =new Questions(9, "Mark the dots corresponding dots for letter U?","easy","10,50,60",1);
        Questions quz_Ten_Easy =new Questions(10, "What is the result of calculation 2 into 3?","easy","10,20,30",1);



        Questions quz_One_Medium = new Questions(1, "Which number should come next in this series?  1,3,5,7,9,...'","medium","10,10",2);
        Questions quz_Two_Medium = new Questions(2, "Fill the missing letters of word doll, D, O,blank, blank?","medium","10,30,50,10,30,50",6);



        Questions quz_One_Hard = new Questions(1, "If you rearrange the letters C,I,F,A,I,P,C, you would have the name of","hard","10,40,50",1);
        Questions quz_One_Hard_Sub1 = new Questions(1, "If you rearrange the letters C,I,F,A,I,P,C,you would have the name of","hard","10,20",2);
        Questions quz_One_Hard_Sub2 = new Questions(1, "If you rearrange the letters C,I,F,A,I,P,C, you would have the name of","hard","10,40",3);
        Questions quz_One_Hard_Sub3 = new Questions(1, "If you rearrange the letters C,I,F,A,I,P,C, you would have the name of","hard","10",4);
        Questions quz_One_Hard_Sub4 = new Questions(1, "If you rearrange the letters C,I,F,A,I,P,C, you would have the name of","hard","10,20,40,50",5);

        Questions quz_Two_Hard=new Questions(2,"Type the corresponding letters of word Cat","hard","10,20",1);
        Questions quz_Two_Hard_Sub1=new Questions(2,"Type the corresponding letters of word Cat","hard","10",2);
        Questions quz_Two_Hard_Sub2=new Questions(2,"Type the corresponding letters of word Cat","hard","20,30,40,50",3);


        dbClass.open();

        dbClass.insertQuestions(quz_One_Easy);
        dbClass.insertQuestions(quz_Two_Easy);
        dbClass.insertQuestions(quz_Three_Easy);
        dbClass.insertQuestions(quz_Four_Easy);
        dbClass.insertQuestions(quz_Five_Easy);
        dbClass.insertQuestions(quz_Six_Easy);
        dbClass.insertQuestions(quz_Seven_Easy);
        dbClass.insertQuestions(quz_Eight_Easy);
        dbClass.insertQuestions(quz_Nine_Easy);
        dbClass.insertQuestions(quz_Ten_Easy);

        dbClass.insertQuestions(quz_One_Medium);
        dbClass.insertQuestions(quz_Two_Medium);


        dbClass.insertQuestions(quz_One_Hard);
        dbClass.insertQuestions(quz_One_Hard_Sub1);
        dbClass.insertQuestions(quz_One_Hard_Sub2);
        dbClass.insertQuestions(quz_One_Hard_Sub3);
        dbClass.insertQuestions(quz_One_Hard_Sub4);

        dbClass.insertQuestions(quz_Two_Hard);
        dbClass.insertQuestions(quz_Two_Hard_Sub1);
        dbClass.insertQuestions(quz_Two_Hard_Sub2);



        tts=new TextToSpeech(this,this);

        Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
        mGestureDetector = new GestureDetector(this, android_gesture_detector);

        passedArg = getIntent().getExtras().getString("type");

        Cursor cursor=dbClass.getQuestionIds(passedArg);

        if (cursor.moveToFirst()){

            qid=cursor.getInt(cursor.getColumnIndex("id"));

        }


        Cursor cursor1 = dbClass.retrieveMaxSeqNo(passedArg);

        if (cursor1.moveToFirst()) {

            seq = cursor1.getInt(cursor1.getColumnIndex("sequence"));
        }

        dbClass.close();


        switch (passedArg){
            case "easy":

                Thread logoTimer = new Thread() {
                    public void run() {
                        try {

                            sleep(2000);
                            speakWords("You have selected Easy level ");
                            sleep(2000);
                            speakWords("Let's check your IQ knowledge on easy level");

                            dbClass.open();
                            Cursor cursor=dbClass.getAllQuestions("easy",qid);

                            if (cursor.moveToFirst()){

                                speakWords("First Question is " + cursor.getString(cursor.getColumnIndex("question")));
                                //  giveInputs("easy");


                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                logoTimer.start();
                break;

            case "medium":

                Thread logoTimer2 = new Thread() {
                    public void run() {
                        try {

                            sleep(2000);
                            speakWords("You have selected Medium level ");
                            sleep(3000);
                            speakWords("Let's check your IQ knowledge in medium level");

                            dbClass.open();
                            Cursor cursor=dbClass.getAllQuestions("medium",qid);
                            if (cursor.moveToFirst()){

                                speakWords("First Question is " + cursor.getString(cursor.getColumnIndex("question")));

                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                logoTimer2.start();
                break;

            case "hard":



                Thread logoTimer3 = new Thread() {
                    public void run() {
                        try {

                            sleep(2000);
                            speakWords("You have selected Hard level ");
                            sleep(3000);
                            speakWords("Let's check your IQ knowledge in hard level");

                            dbClass.open();
                            Cursor cursor=dbClass.getAllQuestions("hard",qid);

                            if (cursor.moveToFirst()){

                                speakWords("First Question is " + cursor.getString(cursor.getColumnIndex("question")));
                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                logoTimer3.start();

                break;
        }
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            else {

            }

        } else {
            Log.e("TTS", "Initialization Failed!");
        }
    }

    private void speakWords(String speech) {

        if (tts != null) {

            tts.setSpeechRate(0.9f);
            tts.setPitch(1.0f);

            tts.speak(speech, TextToSpeech.QUEUE_ADD, null);
        }
        while (tts.isSpeaking() ) {
        };
    }

    public boolean QuzRetrieve()
    {
        dbClass.open();

        qid++;
        Cursor cursor = dbClass.getAllQuestions(passedArg,qid);

        if (cursor.moveToFirst()){

            speakWords("Next question is " + cursor.getString(cursor.getColumnIndex("question")));

        }

        return true;
    }

    public void giveInputs(String type)
    {

        dbClass.open();



        int[] input = new int[seq];

        if(type=="easy")
        {
            if(seq==1) {
                speakWords("Please type the correct answer");
            }

        }

        else if(type=="medium")
        {
            if(seq>1) {
                if(seq==1){
                    speakWords("Please type the first digit of the number");
                }


            }
        }

        else if(type=="hard")
        {

        }
    }


    @Override
    public void onClick(View view) {
        if (!isStarted) {
            isStarted = true;
            String a=(String) view.getTag();
            int id= Integer.parseInt(a);
            mClickedButtonIds.add(id);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            isStarted = false;
                            CheckSpellingEasy(mClickedButtonIds);
                            mClickedButtonIds.clear();
                        }
                    },
                    3000);

        }

        else {
            String a=(String) view.getTag();
            int id= Integer.parseInt(a);
            mClickedButtonIds.add(id);
        }
    }

    public boolean CheckSpellingEasy(List<Integer> number) {

        int[] intArray = new int[number.size()];

        for (int i = 0; i < number.size(); i++) {
            intArray[i] = number.get(i);

        }

        dbClass.open();

        String[] result = dbClass.getAnswers(qid,passedArg);
        String[] array = result[0].split(",");

        for (String elements : array) {
            System.out.print(elements);
            int[] arr = new int[array.length];

            int size = arr.length;
            System.out.print(size);

            for (int j = 0; j < arr.length; j++) {

                arr[j] = Integer.parseInt(array[j]);
            }

            if (Arrays.equals(intArray, arr)) {

                status = "correct";
                marks = 10;
                Marks marks1 = new Marks(qid, passedArg, status, marks);

                m.add(marks1);
                retrieveValuesFromListMethod2(m);

                if(Arrays.equals(intArray, arr)) {
                    break;
                }
            }

            else
            {

                status="incorrect";
                int finalCount=0;

                if(m.size()>0)
                {
                    for(int i=0;i<m.size();i++)
                    {
                        Marks mark = m.get(i);
                        finalCount = finalCount + mark.getMarks();
                    }
                }
                else
                {
                    finalCount=0;
                }


                Intent n1 = new Intent(view_questions.this,view_marks.class);
                n1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Bundle extras = new Bundle();
                extras.putString("marks",String.valueOf(finalCount));
                extras.putIntArray("array", arr);
                n1.putExtras(extras);
                startActivity(n1);

            }
        }

        if(status=="correct") {
            speakWords("Well done. You have given correct answer");
            QuzRetrieve();
        }

        return true;
    }


    public static void retrieveValuesFromListMethod2(List list)
    {
        //Retrieving values from list
        int size = list.size();
        for(int i=0;i<size;i++)
        {
            System.out.println(list.get(i));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);

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
            if (e1.getX() < e2.getX()) {
                Log.d("Gesture ", "Left to Right swipe: "+ e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");


            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: "+ e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

            }
            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", "Up to Down swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");

            }
            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", "Down to Up swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");
            }
            return true;

        }
    }
}
