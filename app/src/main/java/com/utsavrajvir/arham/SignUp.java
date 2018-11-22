package com.utsavrajvir.arham;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    EditText name,username,email,password,mobile;
String s;
JSONObject jsonObject;
JSONArray jsonArray;

SharedPreferences pref;
SharedPreferences.Editor editor;

ProgressBar progressBar;

ArrayList<String> al = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);
        editor = pref.edit();

        progressBar = (ProgressBar)findViewById(R.id.progress_SignUp);


        name = (EditText)findViewById(R.id.name);
        username = (EditText)findViewById(R.id.username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        mobile = (EditText)findViewById(R.id.mobile);

    }

    public void register(View view) {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        NetworkInfo wifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (netInfo == null || wifi == null) {

            finish();
            Intent intent = new Intent(this, Network.class);
            intent.putExtra("name", "Login");
            startActivity(intent);
            return;


        } else {

            progressBar.setVisibility(View.VISIBLE);

           /* BackgroundTask backgroundTask = new BackgroundTask(this);
            try {
                s = backgroundTask.execute("check").get();

                progressBar.setVisibility(View.GONE);

                if(pref.getString("check","Sry").toString().trim().equals("Exist"))
                {
                    jsonObject = new JSONObject(s);
                    jsonArray = jsonObject.getJSONArray("result");
                    int count=0;

                    while(count < jsonArray.length()) {
                        JSONObject jo = jsonArray.getJSONObject(count);
                        al.add(jo.getString("St_Uname"));
                        al.add(jo.getString("St_Email"));

                        count++;

                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
*/
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final Api api = retrofit.create(Api.class);

            Call<List<LoginPojo>> call = api.checkRegistration();

            call.enqueue(new Callback<List<LoginPojo>>() {
                @Override
                public void onResponse(Call<List<LoginPojo>> call, Response<List<LoginPojo>> response) {

                    StringBuffer stringBuffer = new StringBuffer();

                    List<LoginPojo> heroList = response.body();

                    for(LoginPojo ls : heroList){

                        al.add(ls.getStuname());
                        al.add(ls.getStemail());

                    }
                    //l lst.setAdapter(new ArrayAdapter<JSONObject>(getApplicationContext(), android.R.layout.simple_list_item_1, heroList));
                }

                @Override
                public void onFailure(Call<List<LoginPojo>> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            final String name1 = name.getText().toString().trim();
            final String username1 = username.getText().toString().trim();
            final String email1 = email.getText().toString().trim();
            final String password1 = password.getText().toString().trim();
            final String mobile1 = mobile.getText().toString().trim();


            if (TextUtils.isEmpty(name1)) {
                name.setError("Required");
                name.requestFocus();
                return;
            } else if (TextUtils.isEmpty(username1)) {
                username.setError("Required");
                username.requestFocus();
                return;
            } else if (TextUtils.isEmpty(email1)) {
                email.setError("Required");
                email.requestFocus();
                return;
            } else if (TextUtils.isEmpty(password1)) {
                password.setError("Required");
                password.requestFocus();
                ;
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
                email.setError("Please Enter Valid Email Address");
                email.requestFocus();
                return;
            } else if (TextUtils.isEmpty(mobile1)) {
                mobile.setError("Required");
                mobile.requestFocus();
                return;
            }else if(al.contains(username1))
            {
                username.setError("Username already exists");
                username.requestFocus();
                return;
            }else if(al.contains(email1))
            {
                email.setError("Email already exists");
                email.requestFocus();
                return;
            }

            if (password1.length() < 6) {
                password.setError("Length should be equal or grater than 6");
                password.requestFocus();
                return;
            } else {
                /*BackgroundTask backgroundTask1 = new BackgroundTask(this);
                backgroundTask1.execute("SignUp",name1,username1,email1,password1,mobile1);
*/

                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final Api api1 = retrofit1.create(Api.class);

                LoginPojo loginPojo = new LoginPojo(0,name1,username1,email1,password1,mobile1,"","");

                Call<ResponseBody> call1 = RetroClient
                        .getInstance()
                        .getApi()
                        .registration(loginPojo);
                call1.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

                Intent intent = new Intent(this,LoginActivity.class);
                intent.putExtra("name",pref.getString("Name","sry"));
                intent.putExtra("name",pref.getString("Email","sry"));
                startActivity(intent);

//                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }

        }
    }
}
