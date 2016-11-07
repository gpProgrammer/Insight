package com.a2016reserch.sliit.insight.gaming_module;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.a2016reserch.sliit.insight.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class view_marks extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    TextView markresult;
    ImageView image;
    String data = null;
    int finalResult, count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_marks);

        tts = new TextToSpeech(this, this);

        image = (ImageView) findViewById(R.id.imageView);
        markresult = (TextView) findViewById(R.id.textView6);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("marks");
        int []array1=extras.getIntArray("array");

        System.out.print(data);
        System.out.print(array1);

        List<Integer> intList = new ArrayList<Integer>(array1.length);

        for (int i=0; i<array1.length; i++)
        {
            intList.add(array1[i]);
        }
        correcrtAnswerVoice(intList);

        finalResult = Integer.parseInt(data);

        if (finalResult > 0) {
            count = finalResult / 10;
        }

        VoiceResult();

        final Thread logoTimer3 = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    speakWords("You have given incorrect answer. Game is over");
                    sleep(4000);
                    speakWords("you have answered" + count + "questions");
                    sleep(4000);
                    speakWords("Your got" + finalResult + "marks");
                    sleep(4000);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer3.start();
        //VoiceResult();

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

//        while (tts.isSpeaking()) {
//
//        }
    }

    public void VoiceResult() {
        if (finalResult == 0) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);

            Thread logoTimer3 = new Thread() {
                public void run() {
                    try {
//                        sleep(2000);
//                        speakWords("You have given incorrect answer. Game is over");
//                        sleep(1000);
//                        speakWords("you have answered" + count + "questions");
//                        sleep(1000);
//                        speakWords("Your got" + finalResult + "marks");
                        sleep(1000);
                        speakWords("You fall in the category of Feeble-mindedness");
//                        sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer3.start();


        } else if (finalResult >= 0 && finalResult < 20) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);
            Thread logoTimer3 = new Thread() {
                public void run() {
                    try {
//                        sleep(2000);
//                        speakWords("You have given incorrect answer. Game is over");
//                        sleep(1000);
//                        speakWords("you have answered" + count + "questions");
//                        sleep(1000);
//                        speakWords("Your got" + finalResult + "marks");
//                        sleep(1000);
                        speakWords("You fall in the category of Borderline deficiency in intelligence");
                        sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer3.start();

        } else if (finalResult >= 20 && finalResult < 40) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);

            Thread logoTimer3 = new Thread() {
                public void run() {
                    try {
//                        sleep(2000);
//                        speakWords("You have given incorrect answer. Game is over");
//                        sleep(1000);
//                        speakWords("you have answered" + count + "questions");
//                        sleep(1000);
//                        speakWords("Your got" + finalResult + "marks");
                        sleep(1000);
                        speakWords("You fall in the category of Dullness");
                        sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer3.start();

        } else if (finalResult >= 40 && finalResult < 50) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setBackgroundResource(R.drawable.sad);
            Thread logoTimer3 = new Thread() {
                public void run() {
                    try {
//                        sleep(2000);
//                        speakWords("You have given incorrect answer. Game is over");
//                        sleep(1000);
//                        speakWords("you have answered" + count + "questions");
//                        sleep(1000);
//                        speakWords("Your got" + finalResult + "marks");
                        sleep(1000);
                        speakWords("You fall in the category of Average");
                        sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer3.start();

        } else if (finalResult >= 50 && finalResult < 70) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setImageResource(R.drawable.happy_face);
            Thread logoTimer3 = new Thread() {
                public void run() {
                    try {
//                        sleep(2000);
//                        speakWords("You have given incorrect answer. Game is over");
//                        sleep(1000);
//                        speakWords("you have answered" + count + "questions");
//                        sleep(1000);
//                        speakWords("Your got" + finalResult + "marks");
                        sleep(1000);
                        speakWords("You fall in the category of Superior intelligence");
                        sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer3.start();

        } else if (finalResult >= 70 && finalResult < 90) {

            String val = finalResult + "";
            markresult.setText(val);
            image.setImageResource(R.drawable.happy_face);
            Thread logoTimer3 = new Thread() {
                public void run() {
                    try {
//                        sleep(2000);
//                        speakWords("You have given incorrect answer. Game is over");
//                        sleep(1000);
//                        speakWords("you have answered" + count + "questions");
//                        sleep(1000);
//                        speakWords("Your got" + finalResult + "marks");
                        sleep(1000);
                        speakWords("You fall in the category of Very superior intelligence");
                        sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer3.start();

        } else {

            String val = finalResult + "";
            markresult.setText(val);
            image.setImageResource(R.drawable.more_happy_face);
            Thread logoTimer3 = new Thread() {
                public void run() {
                    try {
//                        sleep(2000);
//                        speakWords("You have given incorrect answer. Game is over");
//                        sleep(1000);
//                        speakWords("you have answered" + count + "questions");
//                        sleep(1000);
//                        speakWords("Your got" + finalResult + "marks");
                        sleep(1000);
                        speakWords("You fall in the category of Genius");
                        sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            logoTimer3.start();

        }

    }

    public void correcrtAnswerVoice(List<Integer> number)
    {

  //      List<Integer> list1 = new ArrayList<Integer>(number);

        int[] intArray = new int[number.size()];

        for (int i = 0; i < number.size(); i++) {
            intArray[i] = number.get(i);
        }

        if(Arrays.asList(intArray).contains(10))
        {
            String status="correct";
            speakWords("Correct answer is letter A");
        }
        else
        {
            speakWords("invalid");
        }

    }


}
