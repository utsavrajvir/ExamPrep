package com.utsavrajvir.arham;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

public class category extends AppCompatActivity {


    String json_string = null;
    JSONObject jsonObject1;
    JSONArray jsonArray1;
    CategoryAdapter contactAdapter;
    ListView listView;
    private View view;
    String T_id,M_Cid,M_Time;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.category_listview);
        contactAdapter = new CategoryAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);

        button = (Button)findViewById(R.id.new_button);

        T_id = getIntent().getExtras().getString("T_id");
         M_Cid = getIntent().getExtras().getString("M_Cid");
        M_Time = getIntent().getExtras().getString("M_Time");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                if(netInfo == null){

                    finish();
                    Intent intent1 = new Intent(getApplicationContext(),Network.class);
                    intent1.putExtra("name","First");
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);

                }

                Intent intent = new Intent(getApplicationContext(),Instruction.class);
                intent.putExtra("M_id",M_Cid);
                intent.putExtra("T_id",T_id);
                intent.putExtra("M_Time",M_Time);
                intent.putExtra("cat","category");
                startActivity(intent);

            }
        });

        String s=null;

       /* BackgroundTask backgroundTask = new BackgroundTask(this);

        try {
            s = backgroundTask.execute("category",M_Cid).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //json_string = getIntent().getExtras().getString("json_data");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL+"maincat/"+M_Cid+"/"+"test/"+T_id+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);

        Call<List<CategoryPojo>> call = api.getCategory();

        call.enqueue(new Callback<List<CategoryPojo>>() {
            @Override
            public void onResponse(Call<List<CategoryPojo>> call, Response<List<CategoryPojo>> response) {

                List<CategoryPojo> heroList = response.body();

                for(CategoryPojo cs : heroList){
                        contactAdapter.add(cs);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryPojo>> call, Throwable t) {

            }
        });

       // Toast.makeText(this, M_Cid + " " + M_Time + " "+T_id, Toast.LENGTH_SHORT).show();

       /* String C_id=null;
        String C_Name=null;
        String C_Time=null;
        try {
            jsonObject1 = new JSONObject(s);
            jsonArray1 = jsonObject1.getJSONArray("result");
            int count=0;

            while(count < jsonArray1.length())
            {
                JSONObject jo = jsonArray1.getJSONObject(count);
                C_id = jo.getString("C_id");
                C_Name = jo.getString("C_Name");
                C_Time = jo.getString("C_Time");
                Contacts contacts = new Contacts(C_id,C_Name,C_Time);
                contactAdapter.add(contacts);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                if(netInfo == null){

                    finish();
                    Intent intent1 = new Intent(getApplicationContext(),Network.class);
                    intent1.putExtra("name","First");
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);

                }

                CategoryPojo categoryPojo = (CategoryPojo) parent.getItemAtPosition(position);
                //Contacts ss = (Contacts) parent.getItemAtPosition(position);

                //Toast.makeText(getContext().getApplicationContext(), ss.getM_Cid(), Toast.LENGTH_SHORT).show();

                Intent appInfo = new Intent(getApplicationContext(), Sub_Category.class);

                appInfo.putExtra("C_Id",categoryPojo.getCid());//ss.getMcid()
                appInfo.putExtra("M_Cid",M_Cid);
                appInfo.putExtra("T_Id",T_id);
                appInfo.putExtra("C_Time",categoryPojo.getCtime());//ss.getMtime()
                appInfo.putExtra("cat","category");
                startActivity(appInfo);

                //startActivityForResult(appInfo,1);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


}
