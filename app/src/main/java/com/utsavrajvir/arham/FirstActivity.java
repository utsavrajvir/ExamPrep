package com.utsavrajvir.arham;

import android.content.Intent;
import android.content.SharedPreferences;
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
