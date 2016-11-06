package com.a2016reserch.sliit.insight.findplaces;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.a2016reserch.sliit.insight.InsightHomeActivity;
import com.a2016reserch.sliit.insight.R;
import com.a2016reserch.sliit.insight.findplaces.Logic.GpsLocation;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

// add a comment into Test Branch
public class MainActivity extends Activity implements TextToSpeech.OnInitListener {
    // This code can be any value you want, its just a checksum.
    private static final int MY_DATA_CHECK_CODE = 1234;
    String addressField = "not available";
    Location location = null;
    private GestureDetector mGestureDetector;
    private GestureDetectorCompat gestureDetector;
    private TextToSpeech tts;
    private GpsLocation gpsLocation;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize the gestures detect
        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector();
        mGestureDetector = new GestureDetector(this, android_gesture_detector);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fire off an intent to check if a TTS engine is installed
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, false);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            //return;
//        }
//        location = locationManager.getLastKnownLocation(provider);

        gpsLocation = new GpsLocation(MainActivity.this);

        Thread logoTimer = new Thread() {
            public void run() {
                try {

                    sleep(1500);
                    speakWords("Welcome! to navigation services");
                    sleep(3000);
                    speakWords("Long press to get help");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        logoTimer.start();



    } // end of onCreate method

    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // getLocation method in GpsLocation class
        //location = gpsLocation.getLocation();

    }

    /**
     * Called when the activity has become visible.
     */
    @Override
    protected void onResume() {
        super.onResume();
        // getLocation method in GpsLocation class
        // location = gpsLocation.getLocation();

    }

    /**
     * Called when another activity is taking focus.
     */
    @Override
    protected void onPause() {
        if (tts != null)
        {
            tts.stop();
        }
        super.onPause();

    }

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {

        if (tts != null)
        {
            tts.stop();
        }
        super.onStop();

    }

    /**
     * Called when the activity restarts after stopping it
     */
    @Override
    protected void onRestart() {

        if (tts == null) {
            // success, create the TTS instance
            tts = new TextToSpeech(this, this);
        }
        // getLocation method in GpsLocation class
        //location = gpsLocation.getLocation();
        super.onRestart();

    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {

        if (tts != null)
        {
            tts.stop();
            //tts.shutdown();
        }
        gpsLocation.stopUsingGPS();
        super.onDestroy();

    }


    // deactivate back button
    @Override
    public void onBackPressed() {

        speakWords("back to home page");
        Thread timer = new Thread() {

            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(MainActivity.this, InsightHomeActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }


    /**
     * This is the callback from the TTS engine check, if a TTS is installed we
     * create a new TTS instance (which in turn calls onInit), if not then we will
     * create an intent to go off and install a TTS engine
     *
     * @param requestCode int Request code returned from the check for TTS engine.
     * @param resultCode  int Result code returned from the check for TTS engine.
     * @param data        Intent Intent returned from the TTS check.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                tts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    private void speakWords(String speech) {

        // speak straight away
        if (tts != null) {
            tts.setSpeechRate(1);
            //tts.speak(speech, TextToSpeech.QUEUE_ADD, null);

            // Drop all pending entries in the playback queue.
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);

        }
    }

 //   @Override
//    public void onLocationChanged(Location location) {
//
//        double lat = location.getLatitude();
//        double lng = location.getLongitude();
//
//        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
//        StringBuilder builder = new StringBuilder();
//        try {
//            List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
//            int maxLines = address.get(0).getMaxAddressLineIndex();
//            for (int i = 0; i < maxLines; i++) {
//                String addressStr = address.get(0).getAddressLine(i);
//                builder.append(addressStr);
//                builder.append(" ");
//            }
//
//            String fnialAddress = builder.toString(); //This is the complete address.
//
//            String latituteField = String.valueOf(lat);
//            String longitudeField = String.valueOf(lng);
//            addressField = fnialAddress; //This will display the final address.
//            Toast.makeText(MainActivity.this, "lat = " + lat + " lng = " + lng + " / " + addressField,
//                    Toast.LENGTH_LONG).show();
//
//        }   catch (IOException ex) {
//        ex.printStackTrace();
//        }
//    catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }

    /**
     * Executed when a new TTS is instantiated. Check the whether Language is support or not.
     *
     * @param status
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                Log.e("TTS", "Language is supported");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);


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
            Intent intent = new Intent(MainActivity.this, WalkingDirectionActivity.class);
            startActivity(intent);

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

            location = gpsLocation.getLocation();

                double lat = location.getLatitude();
                double lng = location.getLongitude();

                Geocoder geoCoder = new Geocoder(MainActivity.this, Locale.getDefault());
                StringBuilder builder = new StringBuilder();
                try {
                    List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
                    int maxLines = address.get(0).getMaxAddressLineIndex();
                    for (int i = 0; i < maxLines; i++) {
                        String addressStr = address.get(0).getAddressLine(i);
                        builder.append(addressStr);
                        builder.append(" ");
                    }

                    String fnialAddress = builder.toString(); //This is the complete address.

                    String latituteField = String.valueOf(lat);
                    String longitudeField = String.valueOf(lng);
                    addressField = fnialAddress; //This will display the final address.
                    Toast.makeText(MainActivity.this, "lat = " + lat + " lng = " + lng + " / " + addressField,
                            Toast.LENGTH_LONG).show();

                }   catch (IOException ex) {
                    ex.printStackTrace();
                }
                catch (NullPointerException ex) {
                    ex.printStackTrace();
                }

            speakWords("Your current location is, " + addressField);

            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Thread logoTimer = new Thread() {
                public void run() {
                    try {

                        Log.d("Gesture ", " onLongPress");
                        sleep(3000);
                        speakWords("Navigation service, help.");
                        sleep(3000);
                        speakWords("Double tap, get your current location.");
                        sleep(3000);
                        speakWords("Swipe right, nearest place service.");
                        sleep(3000);
                        speakWords("Swipe left, public transport service.");
                        sleep(3000);
                        speakWords("long press, to get help on any service.");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            logoTimer.start();
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
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
                tts.shutdown();
                Intent intent = new Intent(MainActivity.this, GetNearestActivity.class);
                startActivity(intent);

            }
            if (e1.getX() > e2.getX()) {

                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");

                Intent intent = new Intent(MainActivity.this, GetBusHaltsActivity.class);
                startActivity(intent);

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
