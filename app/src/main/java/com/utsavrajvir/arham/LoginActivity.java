package com.utsavrajvir.arham;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String MYPREF = "myRef";
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    String json_string;
    String name;


    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);
        progressBar = (ProgressBar)findViewById(R.id.progress);

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor  = pref.edit();

    }

    public void login(View view)  {

        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();

        if(TextUtils.isEmpty(email1))
        {
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Please Enter Valid Email Address");
            email.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(password1))
        {
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }
        else if(password1.length() < 6 )
        {
            password.setError("Password Length Should be greater then 6");
            password.requestFocus();
            return;
        }
        else
        {

            BackgroundTask backgroundTask = new BackgroundTask(this);

            progressBar.setVisibility(View.VISIBLE);

            String s = null;
            try {
                s = backgroundTask.execute("login",email1,password1).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();

            }

            progressBar.setVisibility(View.GONE);


            if(pref.getString("result","sry").equals("Exist")) {

                try {
                    jsonObject1 = new JSONObject(s);
                    jsonArray1 = jsonObject1.getJSONArray("result");

                    JSONObject jo = jsonArray1.getJSONObject(0);

                     name = jo.getString("St_Name");
                    String EmailVerification = jo.getString("St_EmailVerified");
                    String MobileVerification = jo.getString("St_MobileVerified");
                    String St_Id = jo.getString("St_Id");
                    String St_MobileNo = jo.getString("St_MobileNo");


                    pref.edit().putString("MobileVerification",MobileVerification).apply();
                    pref.edit().commit();

                    pref.edit().putString("St_Id",St_Id).apply();
                    pref.edit().commit();


                    pref.edit().putString("Name",name).apply();
                    pref.edit().commit();

                    pref.edit().putString("Email",email1).apply();
                    pref.edit().commit();

                    pref.edit().putString("St_MobileNo",St_MobileNo).apply();
                    pref.edit().commit();


                    Toast.makeText(this, email1 + " " + name, Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                }



                finish();
                Intent intent = new Intent(this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("name",name);
                intent.putExtra("email",email1);
                startActivity(intent);
            }

        }


    }

    public void login_signin(View view) {

        finish();
        startActivity(new Intent(this,SignUp.class));
    }
}
