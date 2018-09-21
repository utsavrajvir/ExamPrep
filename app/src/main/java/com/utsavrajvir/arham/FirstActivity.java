package com.utsavrajvir.arham;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String MYPREF = "myRef";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();


        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null){

            finish();
            Intent intent = new Intent(this,Network.class);
            intent.putExtra("name","First");
            startActivity(intent);

        }


        if(pref.getString("result","sry").equals("Exist")) {
            finish();
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("name",pref.getString("Name","sry"));
            intent.putExtra("email",pref.getString("Email","sry"));
            startActivity(intent);
        }

    }

    public void signin(View view) {

        startActivity(new Intent(this,LoginActivity.class));

    }

    public void signup(View view) {

        startActivity(new Intent(this,SignUp.class));

    }
}
