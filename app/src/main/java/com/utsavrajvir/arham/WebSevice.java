package com.utsavrajvir.arham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebSevice extends AppCompatActivity {

    TextView t1;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_sevice);
        t1 = (TextView) findViewById(R.id.textview1);
       lst = (ListView) findViewById(R.id.list1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);

        Call<List<Contacts>> call = api.getHeroes();

        call.enqueue(new Callback<List<Contacts>>() {
            @Override
            public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {

                StringBuffer stringBuffer = new StringBuffer();

                List<Contacts> heroList = response.body();

                for(Contacts ls : heroList){

                    stringBuffer.append(ls.getMname()+"\n");

                }
              //l lst.setAdapter(new ArrayAdapter<JSONObject>(getApplicationContext(), android.R.layout.simple_list_item_1, heroList));

                t1.setText(stringBuffer.toString());
            }

            @Override
            public void onFailure(Call<List<Contacts>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        Hero hero = new Hero("123","Utsav Rajvir Pote","utsavrajvir","utsav123","9924848640","1","1");


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(RetroClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api1 = retrofit1.create(Api.class);

        final Call<ResponseBody> callable = RetroClient
                .getInstance()
                .getApi()
                .getData(hero);



       callable.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               Toast.makeText(WebSevice.this, "Success", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });
 }

    public void View(View view) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","Utsav");
            jsonObject.put("surname","Rajvir");
            jsonObject.put("id","201712076");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String str = jsonObject.toString();
        BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
        try {
            String s =  backgroundTask.execute("view1",str).get();
            t1.setText(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
