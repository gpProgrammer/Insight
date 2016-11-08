package com.a2016reserch.sliit.insight.Messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nimmi on 11/7/2016.
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    private TextToSpeech tts;

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();
                long timeMillis = smsMessage.getTimestampMillis();

                Date date = new Date(timeMillis);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                String dateText = format.format(date);

                smsMessageStr += address +" at "+"\t"+ dateText + "\n";
                smsMessageStr += smsBody + "\n";
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();

            //this will update the UI with message
            ReceiveSms inst = ReceiveSms.instance();
            inst.updateList(smsMessageStr);
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
}
