package com.utsavrajvir.arham;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

                    String name = jo.getString("St_Name");



                    Toast.makeText(this, email1 + " " + name, Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                }



                finish();
                startActivity(new Intent(this, MainActivity.class));
            }

            //startActivity(new Intent(this,MainActivity.class));

            //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

            /*Bundle bundle = new Bundle();
            bundle.putString("json_data", s);

            // set Fragmentclass Arguments
            FragmentOne fragobj = new FragmentOne();
            fragobj.setArguments(bundle);
*/


  //

/*
            Intent intent = new Intent(this,MainActivity.class);
            //intent.putExtra("json_data",s);
            startActivity(intent);
*/

            /*String s_id=null;
            try {
                jsonObject = new JSONObject(s);
                jsonArray = jsonObject.getJSONArray("result");
                int count=0;

                while(count < jsonArray.length())
                {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    s_id = jo.getString("St_Id");
                    count++;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressBar.setVisibility(View.GONE);

            if(pref.getString("result","sry").equals("Exist")) {

                Toast.makeText(this, "User Exist", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, pref.getString("reply", "sry"), Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }

            Toast.makeText(this, s_id, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, pref.getString("reply", "sry"), Toast.LENGTH_SHORT).show();
            // check in database
            */
        }


    }

    public void login_signin(View view) {

        finish();
        startActivity(new Intent(this,SignUp.class));
    }
}
