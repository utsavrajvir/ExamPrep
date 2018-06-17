package com.utsavrajvir.arham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class Verification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);


        this.setTitle("Verification");
    }

    public void send(View view) {


        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("7405644732", "", "sms message", null, null);


        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("Utsav Rajvir", "Try Again");
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);

    }
}
