package com.a2016reserch.sliit.insight.findplaces;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import com.a2016reserch.sliit.insight.R;

public class MainActivity extends Activity implements LocationListener, TextToSpeech.OnInitListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onInit(int status) {

    }
}
