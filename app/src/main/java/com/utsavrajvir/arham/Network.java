package com.utsavrajvir.arham;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Network extends AppCompatActivity {

    String str;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);


        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();

        str = getIntent().getExtras().getString("name");

        /*int ab = 1;
        this.setTitle();
*/

    }

    public void try_again(View view) {

        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo != null){

            finish();
            if(str.equals("First")){

                startActivity(new Intent(this,FirstActivity.class));

            }else if(str.equals("Main")){

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("name",pref.getString("Name","sry"));
                intent.putExtra("email",pref.getString("Email","sry"));
                startActivity(intent);



            }



        }

    }
}
