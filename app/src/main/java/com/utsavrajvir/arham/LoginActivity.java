package com.utsavrajvir.arham;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        NetworkInfo wifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(netInfo == null || wifi == null){

            finish();
            Intent intent = new Intent(this,Network.class);
            intent.putExtra("name","Login");
            startActivity(intent);
            return;

        }else{

            String email1 = email.getText().toString().trim();
            String password1 = password.getText().toString().trim();

            if(TextUtils.isEmpty(email1))
            {
                email.setError("Email or Username is Required");
                email.requestFocus();
                return;
            }else if(TextUtils.isEmpty(password1))
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
                progressBar.setVisibility(View.VISIBLE);

               /* BackgroundTask backgroundTask = new BackgroundTask(this);
                String s = null;
                try {
                    s = backgroundTask.execute("login",email1,password1).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();

                }*/

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final Api api = retrofit.create(Api.class);

                Call<LoginPojo> call = api.checkLogin();

                call.enqueue(new Callback<LoginPojo>() {
                    @Override
                    public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {

                        StringBuffer stringBuffer = new StringBuffer();

                        LoginPojo ls = response.body();

                        if(ls != null) {

                            //stringBuffer.append(ls.getMname()+"\n");
                            //String EmailVerification = ls.get;//jo.getString("St_EmailVerified");
                            String MobileVerification = ls.getStmobileverified();//jo.getString("St_MobileVerified");
                            Integer St_Id = ls.getStid();//jo.getString("St_Id");
                            String St_MobileNo = ls.getStmobileno();//jo.getString("St_MobileNo");
                            String St_Email = ls.getStemail();//jo.getString("St_Email");

                            pref.edit().putString("MobileVerification", MobileVerification).apply();
                            pref.edit().commit();

                            pref.edit().putString("St_Id", St_Id.toString()).apply();
                            pref.edit().commit();


                            pref.edit().putString("Name", name).apply();
                            pref.edit().commit();

                            pref.edit().putString("Email", St_Email).apply();
                            pref.edit().commit();

                            pref.edit().putString("St_MobileNo", St_MobileNo).apply();
                            pref.edit().commit();

                            pref.edit().putString("St_Email", St_Email).apply();
                            pref.edit().commit();

                            //Toast.makeText(this, email1 + " " + name, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginPojo> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                progressBar.setVisibility(View.GONE);

                if(pref.getString("St_Id",null).isEmpty()){

                    Toast.makeText(this, "Invalid Username/Email or password", Toast.LENGTH_SHORT).show();

                }else{

                    finish();
                    Intent intent = new Intent(this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email1);
                    startActivity(intent);
                }
                /*Log.i("result1",pref.getString("result","sry"));
              //  Log.i("s",s);
                //Log.i("ans", String.valueOf(pref.getString("result","sry").equals("Exist")));
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
                        String St_Email = jo.getString("St_Email");

                        pref.edit().putString("MobileVerification",MobileVerification).apply();
                        pref.edit().commit();

                        pref.edit().putString("St_Id",St_Id).apply();
                        pref.edit().commit();


                        pref.edit().putString("Name",name).apply();
                        pref.edit().commit();

                        pref.edit().putString("Email",St_Email).apply();
                        pref.edit().commit();

                        pref.edit().putString("St_MobileNo",St_MobileNo).apply();
                        pref.edit().commit();

                        pref.edit().putString("St_Email",St_Email).apply();
                        pref.edit().commit();

                        //Toast.makeText(this, email1 + " " + name, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                //}
            }
        }

    }

    public void login_signin(View view) {

        finish();
        startActivity(new Intent(this,SignUp.class));
    }
}
